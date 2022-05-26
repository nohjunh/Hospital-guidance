import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel contentPane = new JPanel();
	private JPanel diseasePanel = new JPanel();
	private JPanel hospitalPanel = new JPanel();
	private JPanel hospitalSubPanel = new JPanel();
	private JPanel diseaseLabelPanel = new JPanel();
	private JPanel partLabelPanel = new JPanel();
	private JPanel partBtnPanel = new JPanel();
	private JPanel panel = new JPanel();
	
	private RoundedButton[] partBtn;
	private JButton hospitalButton1;
	private JButton hospitalButton2;
	private JLabel diseaseLabel;
	private JLabel partLabel;
	private JComboBox<String> location1ComboBox;
	private JComboBox<String> location2ComboBox;
	private JTable hospitalTable;
	private JScrollPane scrolledTable;
	
	private PartBtnListener partBtnListener = new PartBtnListener();
	private Font malgunGothic = new Font("맑은 고딕", Font.PLAIN, 13);
	private Font malgunGothicBold = new Font("맑은 고딕", Font.BOLD, 25);
	private Color btnForegroundColor = new Color(50, 50, 50);
	private Color btnBackgroundColor = new Color(210, 224, 239);
	
	private String diseaseSubject = DiseaseInfo.GetDiseaseSubject(0);
	private String hospitalTableContents[][]= KakaoAPI.getHospitalData("서울특별시","종로구","병원");
	private String hospitalTableHeader[]
			= {"병원명","전화번호","위치                                                                                "};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { MainFrame frame = new MainFrame(); frame.setVisible(true); } 
				catch (Exception e) { e.printStackTrace(); }
			}
		});
	}
	
	public MainFrame() {
		setTitle("\uBCF5\uD1B5\uC7A1\uC544");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setResizable(false);
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		setContentPane(contentPane);

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		partLabelPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		partLabelPanel.add(panel);
		partLabelPanel.add(partBtnPanel);
		contentPane.add(partLabelPanel);
		
		partLabel = new JLabel("<html><body style='text-align:center;'></body></html>");
		partLabel.setFont(malgunGothic);
		partLabel.setHorizontalAlignment(SwingConstants.CENTER);
		partLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/image/Body.png")));
		panel.add(partLabel);

		partBtnPanel.setLayout(new GridLayout(3, 3, 10, 10));
		
		partBtn = new RoundedButton[9];
		partBtn[0] = new RoundedButton("우 상");
		partBtn[1] = new RoundedButton("중 상");
		partBtn[2] = new RoundedButton("좌 상");
		partBtn[3] = new RoundedButton("우 중");
		partBtn[4] = new RoundedButton("중 앙");
		partBtn[5] = new RoundedButton("좌 중");
		partBtn[6] = new RoundedButton("우 하");
		partBtn[7] = new RoundedButton("중 하");
		partBtn[8] = new RoundedButton("좌 하");

		for(int i = 0; i < 9; i++) {
			partBtn[i].addActionListener(partBtnListener);
			partBtn[i].setFont(malgunGothicBold);
			partBtn[i].setForeground(btnForegroundColor);
			partBtn[i].setBackground(btnBackgroundColor);

			partBtnPanel.add(partBtn[i]);	
		}
		
		diseasePanel.setLayout(new GridLayout(2, 0, 0, 0));
		contentPane.add(diseasePanel);
		
		diseaseLabelPanel.setLayout(new BoxLayout(diseaseLabelPanel, BoxLayout.X_AXIS));
		diseasePanel.add(diseaseLabelPanel);
		
		diseaseLabel = new JLabel(DiseaseInfo.GetDiseaseName(0));
		diseaseLabel.setFont(malgunGothicBold);
		diseaseLabel.setHorizontalAlignment(JLabel.CENTER);
		diseaseLabelPanel.add(diseaseLabel);
		
		hospitalPanel.setLayout(new BorderLayout(0, 0));
		diseasePanel.add(hospitalPanel);
		
		hospitalTable = new JTable(hospitalTableContents, hospitalTableHeader);
		hospitalTable.setFont(malgunGothic);
		hospitalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		hospitalTable.getColumn("병원명").setPreferredWidth(200);
		hospitalTable.getColumn("전화번호").setPreferredWidth(130);
		hospitalTable.getColumn("위치                                                                                ")
					 .setPreferredWidth(400);
		hospitalTable.setRowHeight(30);
		
		scrolledTable = new JScrollPane(hospitalTable);
		hospitalPanel.add(scrolledTable, BorderLayout.CENTER);
		
		hospitalSubPanel.setBackground(SystemColor.control);
		hospitalPanel.add(hospitalSubPanel, BorderLayout.NORTH);
		
		location1ComboBox = new JComboBox<String>(LocateInfo.getlocateData(0));
		location1ComboBox.setBackground(Color.WHITE);
		location1ComboBox.setFont(malgunGothic);
		hospitalSubPanel.add(location1ComboBox);
		
		location2ComboBox = new JComboBox<String>(LocateInfo.getlocateData(1));
		location2ComboBox.setBackground(Color.WHITE);
		location2ComboBox.setFont(malgunGothic);
		hospitalSubPanel.add(location2ComboBox);
		
		location1ComboBox.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	    location2ComboBox.setVisible(true);
		            JComboBox<?> cb = (JComboBox<?>)e.getSource();
	        		if (cb.getSelectedIndex()==0)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(1)));
	        		else if (cb.getSelectedIndex()==1)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(2)));
	        		else if (cb.getSelectedIndex()==2)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(3)));
	        		else if (cb.getSelectedIndex()==3)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(4)));
	        		else if (cb.getSelectedIndex()==4)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(5)));
	        		else if (cb.getSelectedIndex()==5)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(6)));
	        		else if (cb.getSelectedIndex()==6)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(7)));
	        		else if (cb.getSelectedIndex()==7)
		            	location2ComboBox.setVisible(false);
	        		else if (cb.getSelectedIndex()==8)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(8)));
	        		else if (cb.getSelectedIndex()==9)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(9)));
	        		else if (cb.getSelectedIndex()==10)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(10)));
	        		else if (cb.getSelectedIndex()==11)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(11)));
	        		else if (cb.getSelectedIndex()==12)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(12)));
	        		else if (cb.getSelectedIndex()==13)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(13)));
	        		else if (cb.getSelectedIndex()==14)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(14)));
	        		else if (cb.getSelectedIndex()==15)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(15)));
	        		else if (cb.getSelectedIndex()==16)
		            	location2ComboBox.setModel(new DefaultComboBoxModel<String>(LocateInfo.getlocateData(16)));
		       }
		  });

		hospitalButton1 = new JButton("진료 병원 확인");
		hospitalButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	hospitalTable.setModel(new DefaultTableModel(
            			KakaoAPI.getHospitalData(
            					location1ComboBox.getSelectedItem().toString(),
            					location2ComboBox.getSelectedItem().toString(),
            					diseaseSubject),
            			hospitalTableHeader));
        		hospitalTable.getColumn("병원명").setPreferredWidth(200);
        		hospitalTable.getColumn("전화번호").setPreferredWidth(130);
        		hospitalTable.getColumn("위치                                                                                ")
			              	 .setPreferredWidth(400);
        		hospitalTable.setRowHeight(30);
			}
		});
		hospitalButton1.setFont(malgunGothic);
		hospitalSubPanel.add(hospitalButton1);	
		
		hospitalButton2 = new JButton("추천 병원 확인");
		hospitalButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	hospitalTable.setModel(new DefaultTableModel(
            			CrawlingData.GetCrawlingTable(
            					location1ComboBox.getSelectedItem().toString(),
            					location2ComboBox.getSelectedItem().toString(),
            					diseaseSubject),
            			hospitalTableHeader));
        		hospitalTable.getColumn("병원명").setPreferredWidth(200);
        		hospitalTable.getColumn("전화번호").setPreferredWidth(130);
        		hospitalTable.getColumn("위치                                                                                ")
			            	 .setPreferredWidth(400);
        		hospitalTable.setRowHeight(50);
			}
		});
		hospitalButton2.setFont(malgunGothic);
		hospitalSubPanel.add(hospitalButton2);
	}
	
	class PartBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == partBtn[0]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(1));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(1);;
			}
			else if (e.getSource() == partBtn[1]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(2));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(2);;
			}
			else if (e.getSource() == partBtn[2]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(3));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(3);;
			}
			else if (e.getSource() == partBtn[3]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(4));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(4);;
			}
			else if (e.getSource() == partBtn[4]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(5));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(5);;
			}
			else if (e.getSource() == partBtn[5]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(6));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(6);;
			}
			else if (e.getSource() == partBtn[6]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(7));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(7);;
			}
			else if (e.getSource() == partBtn[7]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(8));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(8);;
			}
			else if (e.getSource() == partBtn[8]) {
				diseaseLabel.setText(DiseaseInfo.GetDiseaseName(9));
				diseaseSubject = DiseaseInfo.GetDiseaseSubject(9);;
			}
		}
	}
	
	public class RoundedButton extends JButton { 
		public RoundedButton() { super(); decorate(); } 
		public RoundedButton(String text) { super(text); decorate(); } 
		public RoundedButton(Action action) { super(action); decorate(); } 
		public RoundedButton(Icon icon) { super(icon); decorate(); } 
		public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
		protected void decorate() { setBorderPainted(false); setOpaque(false); }
		
		@Override protected void paintComponent(Graphics g) { 
			int width = getWidth(); 
			int height = getHeight(); 
			Graphics2D graphics = (Graphics2D) g; 
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			if (getModel().isArmed()) { graphics.setColor(getBackground().darker()); } 
			else if (getModel().isRollover()) { graphics.setColor(getBackground().brighter()); } 
			else { graphics.setColor(getBackground()); } graphics.fillRoundRect(0, 0, width, height, 20, 20); 
			FontMetrics fontMetrics = graphics.getFontMetrics(); 
			Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
			int textX = (width - stringBounds.width) / 2; 
			int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
			graphics.setColor(getForeground()); 
			graphics.setFont(getFont()); 
			graphics.drawString(getText(), textX, textY); 
			graphics.dispose(); super.paintComponent(g); 
		}
	}
}	