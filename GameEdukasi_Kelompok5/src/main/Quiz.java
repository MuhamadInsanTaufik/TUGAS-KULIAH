package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{
	//RAFI
	String[] pertanyaan = 	{
								"Siapa presiden pertama Indonesia?",
								"Dimana ibukota Indonesia berada?",
								"Wilayah termacat di Bandung adalah?",
								"Mata uang negara Jepang yaitu?"
							};
	String[][] opsi = 	{
								{"Soekarno","Jokowi","Megawati","Habibie"},
								{"Jogjakarta","Bandung","Jakarta","Palembang"},
								{"Bojongsoang","Cikaso","Cimahi","Cartil"},
								{"Dollar ","Dinar","Ringgit","Yen"}
							};
	char[] jawaban = 		{
								'A',
								'C',
								'A',
								'D'
							};
	char menebak;
	char answer;
	int index;
	int pertanyaan_benar = 0;
	int total_pertanyaan = 4;
	int hasil;
	int detik=10;
        
	JFrame frame = new JFrame(); //Objek frame buat gui
	JTextField textfield = new JTextField(); //Mendisplay teks pertanyaan yg sedang jalan
	JTextArea textarea = new JTextArea(); //Objek frame untuk mewadahi pertanyaan
	JButton buttonA = new JButton(); //Objek tombol untuk merespon event
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answer_labelA = new JLabel(); //Objek label untuk mendisplay pertanyaan
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	JLabel time_label = new JLabel(); //Objek label untuk mendisplay teks
	JLabel detik_left = new JLabel(); //Objek label untuk mendisplay timer hitungan mundur
	JTextField number_right = new JTextField(); //
	JTextField percentage = new JTextField();
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			detik--;
			detik_left.setText(String.valueOf(detik));
			if(detik<=0) {
				displayAnswer();
			}
			}
		});
	
        //INSAN
	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);
		
                //Frame atau border untuk teks nomor pertanyaan
		textfield.setBounds(0,0,650,50);
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Unispace",Font.BOLD,30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		
                //Frame atau border untuk teks pertanyaannya
		textarea.setBounds(0,50,650,50);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25,25,25));
		textarea.setForeground(new Color(25,255,0));
		textarea.setFont(new Font("Unispace",Font.BOLD,25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		
                //Frame atau border untuk event tombol
		buttonA.setBounds(0,100,100,100);
		buttonA.setFont(new Font("Unispace",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");
		
		buttonB.setBounds(0,200,100,100);
		buttonB.setFont(new Font("Unispace",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");
		
		buttonC.setBounds(0,300,100,100);
		buttonC.setFont(new Font("Unispace",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");
		
		buttonD.setBounds(0,400,100,100);
		buttonD.setFont(new Font("Unispace",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");
		
                //Frame atau border untuk label pertanyaanya
		answer_labelA.setBounds(125,100,500,100);
		answer_labelA.setBackground(new Color(50,50,50));
		answer_labelA.setForeground(new Color(25,255,0));
		answer_labelA.setFont(new Font("Unispace",Font.PLAIN,35));
		
		answer_labelB.setBounds(125,200,500,100);
		answer_labelB.setBackground(new Color(50,50,50));
		answer_labelB.setForeground(new Color(25,255,0));
		answer_labelB.setFont(new Font("Unispace",Font.PLAIN,35));
		
		answer_labelC.setBounds(125,300,500,100);
		answer_labelC.setBackground(new Color(50,50,50));
		answer_labelC.setForeground(new Color(25,255,0));
		answer_labelC.setFont(new Font("Unispace",Font.PLAIN,35));
		
		answer_labelD.setBounds(125,400,500,100);
		answer_labelD.setBackground(new Color(50,50,50));
		answer_labelD.setForeground(new Color(25,255,0));
		answer_labelD.setFont(new Font("Unispace",Font.PLAIN,35));
		
                //Teks timer (hitung mundur)
		detik_left.setBounds(535,510,100,100);
		detik_left.setBackground(new Color(25,25,25));
		detik_left.setForeground(new Color(255,0,0));
		detik_left.setFont(new Font("Unispace",Font.BOLD,60));
		detik_left.setBorder(BorderFactory.createBevelBorder(1));
		detik_left.setOpaque(true);
		detik_left.setHorizontalAlignment(JTextField.CENTER);
		detik_left.setText(String.valueOf(detik));
		
                //Label teks timer
		time_label.setBounds(535,475,100,25);
		time_label.setBackground(new Color(50,50,50));
		time_label.setForeground(new Color(255,0,0));
		time_label.setFont(new Font("Unispace",Font.PLAIN,16));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("timer");
		
                //Teks rasio benar salah
		number_right.setBounds(225,225,200,100);
		number_right.setBackground(new Color(25,25,25));
		number_right.setForeground(new Color(25,255,0));
		number_right.setFont(new Font("Gill Sans FB",Font.BOLD,50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
                //Teks persentase
		percentage.setBounds(225,325,200,100);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(25,255,0));
		percentage.setFont(new Font("Gill Sans FB",Font.BOLD,50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
                //Memanggil objek-objek GUI
		frame.add(time_label);
		frame.add(detik_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		
                //Memanggil pertanyaan selanjutnya
		nextQuestion();
	}
        //FAJAR
	public void nextQuestion() {
		
		if(index>=total_pertanyaan) {
			hasil();
		}
		else {
			textfield.setText("Soal "+(index+1));
			textarea.setText(pertanyaan[index]);
			answer_labelA.setText(opsi[index][0]);
			answer_labelB.setText(opsi[index][1]);
			answer_labelC.setText(opsi[index][2]);
			answer_labelD.setText(opsi[index][3]);
			timer.start();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);
			
			if(e.getSource()==buttonA) {
				answer= 'A';
				if(answer == jawaban[index]) {
					pertanyaan_benar++;
				}
			}
			if(e.getSource()==buttonB) {
				answer= 'B';
				if(answer == jawaban[index]) {
					pertanyaan_benar++;
				}
			}
			if(e.getSource()==buttonC) {
				answer= 'C';
				if(answer == jawaban[index]) {
					pertanyaan_benar++;
				}
			}
			if(e.getSource()==buttonD) {
				answer= 'D';
				if(answer == jawaban[index]) {
					pertanyaan_benar++;
				}
			}
			displayAnswer();
	}
        //NAZWA
	public void displayAnswer() {
		
		timer.stop();
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(jawaban[index] != 'A')
			answer_labelA.setForeground(new Color(255,0,0));
		if(jawaban[index] != 'B')
			answer_labelB.setForeground(new Color(255,0,0));
		if(jawaban[index] != 'C')
			answer_labelC.setForeground(new Color(255,0,0));
		if(jawaban[index] != 'D')
			answer_labelD.setForeground(new Color(255,0,0));
		
		Timer pause = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				answer_labelA.setForeground(new Color(25,255,0));
				answer_labelB.setForeground(new Color(25,255,0));
				answer_labelC.setForeground(new Color(25,255,0));
				answer_labelD.setForeground(new Color(25,255,0));
				
				answer = ' ';
				detik=10;
				detik_left.setText(String.valueOf(detik));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	public void hasil(){
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		hasil = (int)((pertanyaan_benar/(int)total_pertanyaan)*100);
		
		textfield.setText("hasil!");
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		number_right.setText("("+pertanyaan_benar+"/"+total_pertanyaan+")");
		percentage.setText(hasil+"%");
		
		frame.add(number_right);
		frame.add(percentage);
		
	}
}
