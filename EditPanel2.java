import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel2 extends JPanel{
	private JTextField tf = new JTextField(10);
	private TextSource textSource = null;
	private ImageIcon icon = new ImageIcon("d:\\pai2.jpg"); 
    private Image img = icon.getImage(); 
    
	public EditPanel2(TextSource textSource) {
		this.textSource = textSource;
		this.setBackground(Color.WHITE);
		add(tf);
		JButton btn = new JButton("추가");
		add(btn);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = tf.getText();
				if(word.length() ==0)
					return;
				
				textSource.add(word);
			}
		});
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
    }
}
