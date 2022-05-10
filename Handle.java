import java.io.*;
import java.net.*;
  
private static class Handle implements Runnable {
        private final Socket clientSocket;
  
        //Costruttore
        public Handle(Socket socket)
        {
			//Inizializzo il client 
            this.clientSocket = socket;
        }
  
		//Metodo run della classe Runnable
        public void run()
        {
			//Istanzo i buffer 
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                    
                //Buffer 
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
				//Messaggi in arrivos
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.printf(" Sent from the client: %s\n", line);
                    out.println(line);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}