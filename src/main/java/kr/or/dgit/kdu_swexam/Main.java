package kr.or.dgit.kdu_swexam;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.kdu_swexam.initsetting.InitSettingService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	private InitSettingService fileSetting = new InitSettingService();
	private JPanel contentPane;
	private JButton btnInit;
	private JButton btnBackup;
	private JButton btnRestore;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setTitle("DB관리메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 5, 0));
		
		btnInit = new JButton("초기화");
		btnInit.addActionListener(this);
		contentPane.add(btnInit);
		
		btnBackup = new JButton("백업");
		btnBackup.addActionListener(this);
		contentPane.add(btnBackup);
		
		btnRestore = new JButton("복원");
		btnRestore.addActionListener(this);
		contentPane.add(btnRestore);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRestore) {
			actionPerformedBtnRestore(e);
		}
		if (e.getSource() == btnBackup) {
			actionPerformedBtnBackup(e);
		}
		if (e.getSource() == btnInit) {
			actionPerformedBtnInit(e);
		}
	}
	protected void actionPerformedBtnInit(ActionEvent e) {
		fileSetting.initSetting(1, 0);
	}
	protected void actionPerformedBtnBackup(ActionEvent e) {
		fileSetting.initSetting(0, 0);
	}
	protected void actionPerformedBtnRestore(ActionEvent e) {
		fileSetting.initSetting(1, 1);
	}
}
