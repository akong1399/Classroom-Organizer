package com.example.classroomorganizerjava;

import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.util.Log;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.IconCompat.IconType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Classroom {

  public static final String FILE = "schedule.txt";

  private static final String TAG = "Classroom";

  private ArrayList<Student> students;

  public Classroom(Context context) {
    loadFile(context);
  }

  public boolean addStudent(Student student, Context context) {
    if (findStudent(student.getName()) != -1) {
      return false;
    }
    this.students.add(student);
    saveFile(context);
    return true;
  }

  public boolean removeStudent(String name, Context context) {
    int index = findStudent(name);
    if (index == -1) {
      return false;
    }
    this.students.remove(index);
    saveFile(context);
    return true;
  }

  public Student getStudent(String name) {
    int index = findStudent(name);
    if (index != -1) {
      return this.students.get(index);
    }
    return null;
  }

  public boolean checkFile(Context context) {
    return Arrays.asList(context.fileList()).contains(FILE);
  }

  public void clearData(Context context) {
    this.students = new ArrayList<>();
    saveFile(context);
  }

  private int findStudent(String name) {
    for (int i = 0; i < this.students.size(); i++) {
      if (this.students.get(i).getName().equals(name)) {
        return i;
      }
    }
    String names = "";
    for (Student s : this.students) {
      names += " " + s.getName();
    }
    Log.w(TAG,"Student not found\nName passed:" + name + ".");
    Log.w(TAG, "names:" + names + ".");
    return -1;
  }

  private ArrayList<Student> parseString(String data) {
    ArrayList<Student> ret = new ArrayList<>();
    String[] studentArray = data.split("Student: ");
    studentArray = Arrays.copyOfRange(studentArray, 1, studentArray.length);
    for (String student : studentArray) {
      ret.add(new Student(student));
    }
    return ret;
  }

  public void loadFile(Context context) {
    if (checkFile(context)) {
      String data = readFromFile(context);
      this.students = parseString(data);
    }
    else {
      this.students = new ArrayList<>();
      saveFile(context);
    }
  }

  public void saveFile(Context context) {
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
          context.openFileOutput(FILE, Context.MODE_PRIVATE));
      for (Student s : this.students) {
        outputStreamWriter.write(s.toString());
      }
      outputStreamWriter.close();
      Log.i(TAG, "Save File Success");
    } catch (IOException e) {
      Log.e("Exception", "File write failed: " + e.toString());
    }
  }

  private String readFromFile(Context context) {
    String ret = "";
    try {
      InputStream inputStream = context.openFileInput(FILE);
      if (inputStream != null) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String receiveString = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null) {
          stringBuilder.append("\n").append(receiveString);
        }
        inputStream.close();
        ret = stringBuilder.toString();
      }
      Log.i(TAG,"File Read Success");
    } catch (FileNotFoundException e) {
      Log.e(TAG, "File not found");
    } catch (IOException e) {
      Log.e(TAG, "Can not read file");
    }
    return ret;
  }

  public void printFile(Context context) {
    System.out.println(readFromFile(context));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Student s : this.students) {
      sb.append(s.toString()).append("\n\n");
    }
    return sb.toString();
  }

  @RequiresApi(api = VERSION_CODES.O)
  public void initializeFakeData(Context context) {
    clearData(context);
    String oddDays = "MONDAY WEDNESDAY FRIDAY";
    String evenDays = "TUESDAY THURSDAY";
    ArrayList<Task> tasks = new ArrayList<Task>();
    Task t1 = new Task("potty", oddDays, "09:00", "blink", "none", "none");
    Task t2 = new Task("mask", oddDays, "13:00", "blink", "none", "none");
    Task t3 = new Task("potty", evenDays, "10:30", "blink", "none", "none");
    Task t4 = new Task("mask", evenDays, "14:30", "blink", "none", "none");
    tasks.add(t1);
    tasks.add(t2);
    this.addStudent(new Student("Kevin", tasks), context);
    tasks.clear();
    tasks.add(t3);
    tasks.add(t4);
    this.addStudent(new Student("Caves", tasks), context);
    saveFile(context);
  }
}
  /**



  public List<Student> getStudents() {
    return this.students;
  }

  public boolean addTask(String name, String task) {
    Student temp;
    for (Student s : this.students) {
      if (s.getName() == name) {
        s.addTask(task);
        return true;
      }
    }
    return false;
  }

  public boolean addStudent(Student s) {
    return this.students.add(s);
  }

  public boolean removeStudent(Student s) {
    return this.students.remove(s);
  }

  public void loadCSV(Context context) {
    ArrayList<Student> ret = new ArrayList<>();
    String info = readFromFile(FILE, context);
    String[] studentInfo = info.split("Student: ");
    studentInfo = Arrays.copyOfRange(studentInfo, 1, studentInfo.length);
    for (String s : studentInfo) {
      String[] lines = s.split("\n");
      Student temp = new Student(lines[0]);
      for (String line : Arrays.copyOfRange(lines, 1, lines.length)) {
        if (line.contains("Task") || line.contains("]")) {
          continue;
        }
        temp.addTask(new Task(line));
      }
      ret.add(temp);
    }
    this.students = ret;
  }

  public void printCSV(Context context) {
    System.out.println(readFromFile(FILE, context));
  }

  public void saveCSV(Context context) {
    writeToFile(FILE, this.students, context);
  }

  private String readFromFile(String filepath, Context context) {

    String ret = "";

    try {
      InputStream inputStream = context.openFileInput(filepath);

      if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receiveString = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
          stringBuilder.append("\n").append(receiveString);
        }

        inputStream.close();
        ret = stringBuilder.toString();
      }
    } catch (FileNotFoundException e) {
      Log.e("login activity", "File not found: " + e.toString());
      writeToFile(filepath, new ArrayList<>(), context);
    } catch (IOException e) {
      Log.e("login activity", "Can not read file: " + e.toString());
    }

    return ret;
  }

  private void writeToFile(String filepath, ArrayList<Student> data, Context context) {
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
          context.openFileOutput(filepath, Context.MODE_PRIVATE));
      for (Student s : data) {
        outputStreamWriter.write(s.toString());
      }
      outputStreamWriter.close();
      System.out.println("yay!");
    } catch (IOException e) {
      System.out.println("no!");
      Log.e("Exception", "File write failed: " + e.toString());
    }
  }

  @NonNull
  @Override
  public String toString() {
    return "Classroom{" +
        "students=" + students +
        '}';
  }
}
   **/
