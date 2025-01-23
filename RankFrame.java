import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RankFrame extends JFrame { //랭킹 화면
    public RankFrame(RankSource rankSource) {
        setTitle("랭킹");
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RankPanel panel = new RankPanel(rankSource);
        setContentPane(panel);
        setVisible(true);
    }

    class RankPanel extends JPanel {
    	private ImageIcon icon = new ImageIcon("d:\\top.jpg"); 
 	    private Image img = icon.getImage();
 	    
        public RankPanel(RankSource rankSource) {
            setLayout(null);
            ArrayList<String[]> scores = rankSource.getSortedScores(); //정렬된 점수 가져오기

            int y = 110; //레이블 y위치
            int rank = 1; //순위 카운터
            
            for (String[] score : scores) { //점수 목록 돌면서 레이블 생성
                JLabel label = new JLabel(score[0] + " - " + score[1] + "점");
                label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
                label.setBounds(280, y, 300, 30);
                label.setForeground(Color.WHITE);
                add(label);
                
                y += 65;
                rank++;
                if (rank > 5) break; // 상위 5명만 표시
            }
            
            BlockButton btn = new  BlockButton(Color.WHITE, Color.BLACK);
			btn.setText("시작 화면으로");
			btn.setLocation(250,420);
			btn.setSize(130, 50);
			add(btn);
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Initgame(); //초기 화면으로 돌아감
					dispose();
				}
			});
        }
        
        @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g); 
	        g.drawImage(img, 0, 0, getWidth(),getHeight(), this); 
	    }

    }
}
