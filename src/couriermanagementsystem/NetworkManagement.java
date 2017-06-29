package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NetworkManagement {
	public NetworkManagement(){
		JFrame frame = new JFrame();
		frame.setTitle("Network Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Manage Intersection");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel blockedEdgesLabel = new JLabel("Blocked Intersections");
		String[] blockedEdges = Main.networkManager.getBlockedEdges(); 
		String[][] blockTableContent = new String[blockedEdges.length][3];
		for (int i = 0; i < blockedEdges.length; i++) {
			blockTableContent[i][0] = blockedEdges[i].split("-")[0];
			blockTableContent[i][1] = blockedEdges[i].split("-")[1];
			blockTableContent[i][2] = blockedEdges[i].split("-")[2];
		}
		String column[]={"Start Node", "End Node", "Direction"};
		JTable jt = new JTable(blockTableContent,column);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));
		jt.setFillsViewportHeight(true);
		JScrollPane blockedEdgesField = new JScrollPane(jt);
		JButton unblock = new JButton("UnBlock Intersection");
		unblock.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int rowNumer = jt.getSelectedRow();
				if(rowNumer == -1){
					Main.raiseAlert("Please Select an Edge to UnBlock");
					return;
				}
		  		String edge = jt.getValueAt(rowNumer, 0).toString()+"-"+jt.getValueAt(rowNumer, 1).toString()+"-"+jt.getValueAt(rowNumer, 2).toString();
		  		Main.networkManager.unblockEdge(edge);
		  		frame.dispose();
		  		new NetworkManagement();
			}
		});
		JLabel unblockedEdgesLabel = new JLabel("UnBlocked Intersections");
		String[] unblockedEdges = Main.networkManager.getUnBlockedEdges();
		String[][] unBlockTableContent = new String[unblockedEdges.length][3];
		for (int i = 0; i < unblockedEdges.length; i++) {
			unBlockTableContent[i][0] = unblockedEdges[i].split("-")[0];
			unBlockTableContent[i][1] = unblockedEdges[i].split("-")[1];
			unBlockTableContent[i][2] = unblockedEdges[i].split("-")[2];
		}
		JTable jt2 = new JTable(unBlockTableContent,column);
		jt2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt2.setPreferredScrollableViewportSize(new Dimension(450,63));
		jt2.setFillsViewportHeight(true);
		JScrollPane unblockedEdgesField = new JScrollPane(jt2);
		JButton block = new JButton("Block Intersection");
		block.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int rowNumer = jt2.getSelectedRow();
				if(rowNumer == -1){
					Main.raiseAlert("Please Select an Edge to Block");
					return;
				}
				String edge = jt2.getValueAt(rowNumer, 0).toString()+"-"+jt2.getValueAt(rowNumer, 1).toString()+"-"+jt2.getValueAt(rowNumer, 2).toString();
		  		Main.networkManager.blockEdge(edge);
		  		frame.dispose();
		  		new NetworkManagement();
			}
		});
		//adding content to group
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(blockedEdgesLabel)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(blockedEdgesField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(unblock)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(unblockedEdgesLabel)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(unblockedEdgesField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(block)
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
				.addComponent(blockedEdgesLabel)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(blockedEdgesField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(unblock)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(unblockedEdgesLabel)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(unblockedEdgesField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(block)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(100)
			)
		);
		//frame packing and visibility set
		frame.pack();
		frame.setVisible(true);
	}
}
