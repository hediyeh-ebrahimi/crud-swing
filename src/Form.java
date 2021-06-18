import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Form extends JFrame {

    private StudentService studentService;

    //labels
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblStdNumber;

    //txts
    private JTextField txtFirstName;
    private JTextField txtStdNumber;
    private JTextField txtLastName;

    //btns
    private JButton btnDelete;
    private JButton btnSave;

    private JScrollPane scrollPane;
    private JTable tblStudents;

    public Form() {
        this.initComponents();
        this.studentService = new StudentService();
        this.getAll();
    }


    private void initComponents() {
        //first-name
        this.txtFirstName = new JTextField();
        this.lblFirstName = new JLabel();

        //last-name
        this.lblLastName = new JLabel();
        this.txtLastName = new JTextField();

        //student-number
        this.txtStdNumber = new JTextField();
        this.lblStdNumber = new JLabel();

        //btns
        this.btnSave = new JButton();
        JButton btnUpdate = new JButton();
        this.btnDelete = new JButton();

        this.scrollPane = new JScrollPane();
        this.tblStudents = new JTable();

        this.lblFirstName.setText("نام");
        this.lblLastName.setText("نام خانوادگی");
        this.lblStdNumber.setText("شماره دانشجویی");
        this.tblStudents.setModel(new DefaultTableModel(new Object[0][], new String[]{"شماره دانشجویی", "نام خانوادگی", "نام"}) {
            boolean[] canEdit = new boolean[]{false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });

        txtFirstName.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
        txtLastName.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );

        this.tblStudents.setCellSelectionEnabled(true);
        this.tblStudents.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Form.this.tblStudentsMouseClicked(evt);
            }
        });

        this.scrollPane.setViewportView(this.tblStudents);
        if (this.tblStudents.getColumnModel().getColumnCount() > 0) {
            this.tblStudents.getColumnModel().getColumn(2).setResizable(false);
        }

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Form.this.formClicked(evt);
            }
        });

        //New btn
        this.btnSave.setText("جدید");
        this.btnSave.setBackground(Color.blue);
        this.btnSave.setBorder(new SoftBevelBorder(0));
        this.btnSave.setIconTextGap(0);
        this.btnSave.setInheritsPopupMenu(true);
        this.btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Form.this.btnSaveActionPerformed(evt);
            }
        });

        //Upadte btn
        btnUpdate.setText("ویرایش");
        btnUpdate.setBackground(Color.green);
        btnUpdate.setBorder(new SoftBevelBorder(0));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Form.this.btnUpdateActionPerformed(evt);
            }
        });

        //Delete btn
        this.btnDelete.setText("حذف");
        this.btnDelete.setBackground(Color.red);
        this.btnDelete.setBorder(new SoftBevelBorder(0));
        this.btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Form.this.btnDeleteActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap(-1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(this.txtFirstName, -2, 163, -2)
                                                .addGap(18, 18, 18).addComponent(this.lblFirstName, -2, 74, -2))
                                        .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.txtLastName, -2, 163, -2)
                                                .addComponent(this.txtStdNumber, -2, 163, -2))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(this.lblStdNumber, -2, 74, -2)
                                                        .addComponent(this.lblLastName, -2, 86, -2))))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, 32767))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.btnDelete, -2, 72, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                                        .addComponent(btnUpdate, -2, 75, -2).addGap(27, 27, 27)
                                        .addComponent(this.btnSave, -2, 75, -2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.scrollPane, -2, 376, -2)
                        .addContainerGap()).addGroup(layout.createSequentialGroup().addGap(218, 218, 218)
                ));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.txtFirstName, -2, 32, -2)
                                        .addComponent(this.lblFirstName, -2, 24, -2)
                                )
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.txtLastName, -2, 32, -2)
                                                .addComponent(this.lblLastName, -2, 24, -2)).addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.txtStdNumber, -2, 32, -2)
                                                .addComponent(this.lblStdNumber, -2, 24, -2)).addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.btnDelete, -2, 39, -2).addComponent(btnUpdate, -2, 39, -2)).addComponent(this.btnSave, -2, 39, -2)))
                                .addComponent(this.scrollPane, GroupLayout.Alignment.TRAILING, -2, 234, -2)).addContainerGap(29, 32767)));


        this.pack();
    }

    private void formClicked(MouseEvent evt) {
        this.txtStdNumber.setEditable(true);
        this.clear();
    }


    private void btnSaveActionPerformed(ActionEvent evt) {
        String firstName = this.txtFirstName.getText().trim();
        String lastName = this.txtLastName.getText().trim();
        String stdNumber = this.txtStdNumber.getText().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !stdNumber.isEmpty()) {
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setStdNumber(stdNumber);
            this.studentService.add(student);

            this.txtStdNumber.setEditable(true);
            this.clear();
            this.getAll();
        } else {
            this.alert("تمام فیلدها ضروری می باشد.");
        }

    }


    private void getAll() {

        List<Student> allStudent = this.studentService.getAll();
        DefaultTableModel model = (DefaultTableModel) this.tblStudents.getModel();
        model.setRowCount(0);

        for (Student student : allStudent) {
            Object[] row = new Object[]{student.getStdNumber(), student.getLastName(), student.getFirstName()};
            model.addRow(row);
        }

    }

    private void tblStudentsMouseClicked(MouseEvent evt) {
        int i = this.tblStudents.getSelectedRow();
        TableModel model = this.tblStudents.getModel();
        this.txtStdNumber.setText(model.getValueAt(i, 0).toString());
        this.txtLastName.setText(model.getValueAt(i, 1).toString());
        this.txtFirstName.setText(model.getValueAt(i, 2).toString());
        this.txtStdNumber.setEditable(false);
    }

    private void btnDeleteActionPerformed(ActionEvent evt) {


        int i = this.tblStudents.getSelectedRow();
        if (i >= 0) {
            int option = JOptionPane.showConfirmDialog(this.rootPane, "آیا از حذف آیتم مورد نظر اطمینان دارید؟", "حذف", 0);
            if (option == 0) {
                TableModel model = this.tblStudents.getModel();
                String id = model.getValueAt(i, 2).toString();
                if (this.tblStudents.getSelectedRows().length == 1) {
                    String firstName = this.txtFirstName.getText().trim();
                    String lastName = this.txtLastName.getText().trim();
                    String stdNumber = this.txtStdNumber.getText().trim();

                    Student student = new Student();
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setStdNumber(stdNumber);
                    this.studentService.delete(student);

                    DefaultTableModel model1 = (DefaultTableModel) this.tblStudents.getModel();
                    model1.setRowCount(0);

                    this.txtStdNumber.setEditable(true);
                    this.clear();
                    this.getAll();
                }
            }
        }

    }

    private void btnUpdateActionPerformed(ActionEvent evt) {
        this.txtStdNumber.setEditable(false);
        String firstName = this.txtFirstName.getText().trim();
        String lastName = this.txtLastName.getText().trim();
        String stdNumber = this.txtStdNumber.getText().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !stdNumber.isEmpty()) {
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setStdNumber(stdNumber);
            this.studentService.update(student);
            this.txtStdNumber.setEditable(true);
            this.clear();
            this.getAll();
        } else {
            this.alert("تمام فیلدها ضروری می باشد.");

        }

    }

    public void alert(String msg) {
        JOptionPane.showMessageDialog(this.rootPane, msg);
    }

    private void clear() {
        this.txtFirstName.setText("");
        this.txtLastName.setText("");
        this.txtStdNumber.setText("");
    }


}