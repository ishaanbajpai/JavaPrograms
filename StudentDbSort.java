import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionEvent;

public class StudentDbSort {

	private JFrame frmStudentDb;
	private JTextField nameField;
	private JTextField rollField;
	private JTextField marksField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDbSort window = new StudentDbSort();
					window.frmStudentDb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentDbSort() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<Student> list = new ArrayList<Student>();
		Student s1 = new Student("Ishaan",1446,95);
		Student s2 = new Student("Khushaan",1413,65);
		Student s3 = new Student("Rupesh",1417,90);
		Student s4 = new Student("Bhavya",1445,70);
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		
		frmStudentDb = new JFrame();
		frmStudentDb.setTitle("Student DB");
		frmStudentDb.getContentPane().setBackground(new Color(153, 204, 255));
		frmStudentDb.setBounds(100, 100, 450, 300);
		frmStudentDb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentDb.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(86, 45, 148, 19);
		frmStudentDb.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		rollField = new JTextField();
		rollField.setColumns(10);
		rollField.setBounds(86, 74, 148, 19);
		frmStudentDb.getContentPane().add(rollField);
		
		marksField = new JTextField();
		marksField.setColumns(10);
		marksField.setBounds(86, 103, 148, 19);
		frmStudentDb.getContentPane().add(marksField);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBounds(10, 48, 45, 13);
		frmStudentDb.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ROLL NO.");
		lblNewLabel_1.setBounds(10, 77, 45, 13);
		frmStudentDb.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MARKS");
		lblNewLabel_2.setBounds(10, 106, 45, 13);
		frmStudentDb.getContentPane().add(lblNewLabel_2);
		
		JTextArea resultScreen = new JTextArea();
		resultScreen.setBounds(10, 155, 416, 98);
		frmStudentDb.getContentPane().add(resultScreen);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 143, 416, 2);
		frmStudentDb.getContentPane().add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("ENTER DATA:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setBounds(10, 10, 179, 25);
		frmStudentDb.getContentPane().add(lblNewLabel_3);
		
		JButton addButton = new JButton("ADD DATA");//add data to the arrayList
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1 = nameField.getText();
				int val1 = Integer.parseInt(rollField.getText());
				int val2 = Integer.parseInt(marksField.getText());
				list.add(new Student(str1,val1,val2));//arrayList method to add new student
				
				//reset the fields
				nameField.setText("");
				rollField.setText("");
				marksField.setText("");
			}
		});
		addButton.setBounds(258, 44, 85, 21);
		frmStudentDb.getContentPane().add(addButton);
		
		JButton listButton = new JButton("LIST");
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newline = "\n";
				String stuData = " ";
				for(Student s:list) {
					stuData+=(String.valueOf(s)+newline);
				}
				resultScreen.setText("Name"+" "+"Roll No."+" "+"Marks"+newline+stuData);
			}
		});
		listButton.setBounds(341, 44, 85, 21);
		frmStudentDb.getContentPane().add(listButton);
		
		String sortBy[] = {"Name","RollNo","Marks"};
		
		JComboBox sortList = new JComboBox(sortBy);
		//sortList.setModel(new DefaultComboBoxModel(new String[] {"Name", "Roll No.", "Marks"}));
		sortList.setBounds(304, 102, 85, 21);
		frmStudentDb.getContentPane().add(sortList);
		sortList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(sortList.getItemAt(sortList.getSelectedIndex()));
				
				if(str.equals("Name"))
				{
					Collections.sort(list, new Sortbyname()); 
				}
				else if(str.equals("RollNo"))
				{
					Collections.sort(list,new Sortbyrno());
				}
				else
				{
					Collections.sort(list,new Sortbymarks());
				}
				
				String newline = "\n";
				String stuData = " ";
				for(Student s:list) {
					stuData+=(String.valueOf(s)+newline);
				}
				resultScreen.setText("Name"+" "+"Roll No."+" "+"Marks"+newline+stuData);
			}
		});
	}
}

class Student{
	
	String name;
	int rollno;
	int marks;
	
	public Student(String name, int rollno, int marks) {
		this.name = name;
		this.rollno = rollno;
		this.marks = marks;
	}
	
	@Override
	public String toString(){
		return name+" "+rollno+" "+marks;		
	}
}
class Sortbyname implements Comparator<Student>
{
	public int compare(Student a, Student b) 
	{
	return a.name.compareTo(b.name);
	}
}
class Sortbyrno implements Comparator<Student>
{
	public int compare(Student a, Student b) 
	{
	return a.rollno - b.rollno; 
	}
}
class Sortbymarks implements Comparator<Student>
{
	public int compare(Student a, Student b) 
	{
	return a.marks- b.marks; 
	}
}
