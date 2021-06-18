import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StudentService {

    public StudentService() {
    }

    public List<Student> getAll() {
        FileReader fileReader = null;
        List<Student> students = new ArrayList<>();
        try {
            fileReader = new FileReader("src/db.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length > 0) {
                    Student student = new Student();
                    if (words[0] != null) {
                        student.setFirstName(words[0]);
                    }
                    if (words[1] != null) {
                        student.setLastName(words[1]);
                    }
                    if (words[2] != null) {
                        student.setStdNumber(words[2]);
                    }

                    students.add(student);

                }
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void add(Student student) {
        BufferedWriter writer = null;
        try {
            // Read the content from file
            boolean newLine = false;
            try (FileReader fileReader = new FileReader("src/db.txt")) {
                int ch = fileReader.read();
                while (ch != -1) {
                    newLine = true;
                    break;
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                // Exception handling
            } catch (IOException e) {
                // Exception handling
            }


            writer = new BufferedWriter(new FileWriter("src/db.txt", true));
            if (newLine) {
                writer.newLine();

            }
            writer.write(student.getFirstName() + "," + student.getLastName() + "," + student.getStdNumber());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Student student) {
        FileReader fileReader = null;
        BufferedWriter writer = null;
        try {
            fileReader = new FileReader("src/db.txt");
            writer = new BufferedWriter(new FileWriter("src/dbTmp.txt", true));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(student.getFirstName() + "," + student.getLastName() + "," + student.getStdNumber())) {
                    continue;
                } else {
                    String[] words = line.split(",");
                    Student studentExist = new Student();

                    studentExist.setFirstName(words[0]);
                    studentExist.setLastName(words[1]);
                    studentExist.setStdNumber(words[2]);

                    writer.write(studentExist.getFirstName() + "," + studentExist.getLastName() + "," + studentExist.getStdNumber());
                    writer.newLine();
                }
            }

            fileReader.close();
            writer.close();
            File fileDb = new File("./src/db.txt");
            boolean resDel = fileDb.delete();
            File filePre = new File("src/dbTmp.txt");
            File fileNew = new File("src/db.txt");
            boolean resRename = filePre.renameTo(fileNew);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(Student student) {
        FileReader fileReader = null;
        BufferedWriter writer = null;
        try {
            fileReader = new FileReader("src/db.txt");
            writer = new BufferedWriter(new FileWriter("src/dbTmp.txt", true));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(","+student.getStdNumber())) {
                    Student studentUpdated = new Student();

                    studentUpdated.setFirstName(student.getFirstName());
                    studentUpdated.setLastName(student.getLastName());
                    studentUpdated.setStdNumber(student.getStdNumber());

                    writer.write(studentUpdated.getFirstName() + "," + studentUpdated.getLastName() + "," + studentUpdated.getStdNumber());
                    writer.newLine();
                } else {
                    String[] words = line.split(",");
                    Student studentExist = new Student();

                    studentExist.setFirstName(words[0]);
                    studentExist.setLastName(words[1]);
                    studentExist.setStdNumber(words[2]);

                    writer.write(studentExist.getFirstName() + "," + studentExist.getLastName() + "," + studentExist.getStdNumber());
                    writer.newLine();
                }
            }
            fileReader.close();
            writer.close();
            File fileDb = new File("./src/db.txt");
            boolean resDel = fileDb.delete();
            File filePre = new File("src/dbTmp.txt");
            File fileNew = new File("src/db.txt");
            boolean resRename = filePre.renameTo(fileNew);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
