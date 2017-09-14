
import java.util.HashMap;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server1 {

	public static final int BASE_PORT = 2000;

    private ServerSocket serverSocket;

    public Server(int socket) throws IOException {
        serverSocket = new ServerSocket(socket);
        // create a new server socket 
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

        BufferedReader in = new
                BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new
                PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
        String line;
		String[] details = new String[3];
		int i;
        for(i=0; i<3 ; i++ ) {
			out.print("\n");
			out.flush();
			line = in.readLine();
            System.out.println(line);
			details[i] = line;
			
			if(i ==1){
				if(map.containsKey(details[1])){
					out.print(map.get(details[1]) + "\n");
					out.flush();
				}else{
					out.print("-1\n");
					out.flush();
				}
			}
				map.replace(details[1] , details[2]);
        }
    }
	
	
	
	
	
	
	
	
	
	http://jm2pc.sourceforge.net/


    private static String line;
	 public static HashMap<String,String > map = new HashMap<>();

    public static void main(String[] args) throws Exception{

        try{
            BufferedReader br = new BufferedReader(new FileReader("stocks.csv"));

            // read line by line of the file

            while((line = br.readLine()) != null){
                String[] raw = line.split(",");          // split data using comma

                map.put(raw[0],raw[2]);           // store data using first name as the key
            }
        }
		 

        catch(Exception e){
            e.printStackTrace();
        }

		Server server = new Server(BASE_PORT);
		server.server_loop();

    }
}
