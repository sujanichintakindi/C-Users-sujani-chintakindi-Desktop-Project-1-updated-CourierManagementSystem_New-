package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class BaseParametersManagement {
	public BaseParametersManagement() {
		JFrame frame = new JFrame();
		frame.setTitle("Update Base Parameters");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Update Base Parameters");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel costLabel = new JLabel("Cost");
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    JFormattedTextField costField = new JFormattedTextField(formatter);
		JLabel timeLabel = new JLabel("Time");
		NumberFormat format2 = NumberFormat.getInstance();
	    NumberFormatter formatter2 = new NumberFormatter(format2);
	    formatter2.setValueClass(Integer.class);
	    formatter2.setMinimum(0);
	    formatter2.setMaximum(Integer.MAX_VALUE);
	    formatter2.setAllowsInvalid(false);
	    formatter2.setCommitsOnValidEdit(true);
	    JFormattedTextField timeField = new JFormattedTextField(formatter2);
		JLabel distanceLabel = new JLabel("Distance");
		NumberFormat format3 = NumberFormat.getInstance();
	    NumberFormatter formatter3 = new NumberFormatter(format3);
	    formatter3.setValueClass(Integer.class);
	    formatter3.setMinimum(0);
	    formatter3.setMaximum(Integer.MAX_VALUE);
	    formatter3.setAllowsInvalid(false);
	    formatter3.setCommitsOnValidEdit(true);
	    JFormattedTextField distanceField = new JFormattedTextField(formatter3);
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(costField.getValue() == null || timeField.getValue() == null || distanceField.getValue() == null){
						Main.raiseAlert("Please enter all values");
						return;
					}
					int cost = (Integer) costField.getValue();
					int time = (Integer) timeField.getValue();
					int distance = (Integer) distanceField.getValue();
					PrintWriter writer = new PrintWriter("baseparams.csv", "UTF-8");
					writer.println(cost+","+distance+","+time);
					writer.close();
					frame.dispose();
					Main.raiseAlert("Updated Succesfully");
					Main.baseParams = new BaseParameters();
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					System.out.println(e1);
				}
			}
		});
		ok.setMinimumSize(new Dimension(250, 20));
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(costLabel)
				.addGap(20)
				.addComponent(costField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(timeLabel)
				.addGap(20)
				.addComponent(timeField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(distanceLabel)
				.addGap(20)
				.addComponent(distanceField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(ok)
				.addGap(75)
			)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(costLabel)
				.addComponent(costField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(timeLabel)
				.addComponent(timeField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(distanceLabel)
				.addComponent(distanceField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(ok)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(100)
			)
		);
		//frame packing and visibility set
		frame.pack();
		frame.setVisible(true);
		costLabel.setText("Cost("+Main.baseParams.getCost()+")");
		distanceLabel.setText("Distance("+Main.baseParams.getDistance()+")");
		timeLabel.setText("Time("+Main.baseParams.getTime()+")");
	}
}
