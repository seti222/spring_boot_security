package pe.seti222.config.filter;

public class FmsUrlParser {

	public String parse(String requestURI) {
		// TODO Auto-generated method stub
		String resultURI = requestURI;
		int i = resultURI.lastIndexOf("/");
		if(resultURI.lastIndexOf("/") > 0){
			String lastPath  = resultURI.substring(i+1,resultURI.length());
			try {
				long l = Long.parseLong(lastPath);
				resultURI = requestURI.substring(0,i);
			} catch (NumberFormatException nfe){
			
			}
			
		}
		return resultURI;
	}

}
