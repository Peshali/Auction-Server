import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AuctionServer{
    public static final int BASE_PORT = 2000;     
    
    private ServerSocket serverSocket; 
	public static HashMap<String,String> prices= new HashMap<>();
	public static HashMap<String,String[]> clientBid= new HashMap<>();
    
    public AuctionServer(int socket) throws IOException { 
		serverSocket = new ServerSocket(socket); 
    }

    public void server_loop() throws IOException { 
		while(true) { 
			Socket socket = serverSocket.accept(); 			
			try { 
				handle(socket); 
			} catch (IOException e) { 
				System.out.println(e); 
			} finally { 
				socket.close();
			}
		}
    }

    private void handle(Socket socket) throws IOException { 
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		String line; 
		String [] details = new String[2];
		String Name ="";
		int i=0;
		for(line = in.readLine();  line != null && !line.equals("quit");  line = in.readLine()) {
			System.out.println(line);
			
			if(i==0){
				Name=line;
			}else if(i%2==1){
				details[0]=line;
				if(prices.containsKey(line)){
					out.print("0\n");
					/*System.out.println("yy\n");
					out.print(prices.get(line)+"\n");*/
					out.flush();
				}else{
					out.print("-1\n");
					out.flush();
					i=i-1;
					continue;
				}
			}else{
				details[1]=line;
			}
			clientBid.put(Name,details);
			prices.replace(details[0],details[1]);
			i=i+1;
		}
    }

	public static void main (String args[])  throws IOException{
		
		try {
            BufferedReader br = new BufferedReader(new FileReader("stocks.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
				prices.put(details[0],details[2]);
            }

        }catch(Exception e){
            System.out.println(e.toString());
		}
		AuctionServer server = new AuctionServer(BASE_PORT);
		server.server_loop(); 
	}

}