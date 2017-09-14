
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUI{
	
	
	public static void createTable(String []price , String name){

		JFrame frame = new JFrame(name + "_Bid_Table");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Object rowdata[][] = new Object[8][3];

		Object columnNames[] = { "Client Name", "Symbol", "Price" };
		
		rowdata[0] = new Object[]{"FB" , "Facebook" , price[0]};
		rowdata[1] = new Object[]{"VRTU" , "Virtusa Corporation - common stock" , price[1]};
		rowdata[2] = new Object[]{"MSFT" , "Microsoft Corporation - Common Stock" , price[2]};
		rowdata[3] = new Object[]{"GOOGL" , "Google Inc. - Class C Capital Stock" , price[3]};
		rowdata[4] = new Object[]{"YHOO" , "Yahoo! Inc. - Common Stock" , price[4]};
		rowdata[5] = new Object[]{"XLNX" , "Xilinx" , price[5]};
		rowdata[6] = new Object[]{"TSLA" , "Tesla Motors" , price[6]};
		rowdata[7] = new Object[]{"TXN" , "Texas Instruments Incorporated - Common Stock" , price[7]};
		
		JTable table = new JTable(rowdata, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}

}
