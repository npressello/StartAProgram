package startaprogram.npressello.com.github;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class StartAProgram {
	
	public static void main(String[] args) {
		String dir = obtainDir();
		String url = "http://www.ole.com.ar/estadisticas/futbol/primera-division.html#posiciones_tab";
		Process process = null;
		boolean hasChanged = false;
		String dataToCheck = "";
		try {
			process = buildProcess(dir);
			Thread.sleep(10000);
			dataToCheck = getWebPage(url);
			while (hasChanged) {
				if (Integer.valueOf(dataToCheck) == Integer.valueOf(getWebPage(url))) {
					System.out.println("The results are the same");
					Thread.sleep(2000);
				} else
					hasChanged = true;
			}
			Runtime.getRuntime().exec("taskkill /F /IM ccleaner64.exe");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {			
			if (process != null) System.out.println("asdsad"); 
		}
		
	}
	
	public static String getWebPage(String webUrl) throws IOException {
		String response = "";
		Document doc = Jsoup.connect(webUrl).get();
		Elements teams = doc.select("#positionsTable0").select(".pts");
		response = teams.first().ownText();		
		return response;
	}
	
	public static Process buildProcess(String dir) throws IOException {
		Process process = new ProcessBuilder(dir).start();
		return process;
	}
	
	public static String obtainDir() {
		Scanner sc = new Scanner(System.in);
		String dir = "";
		String[] dirSplit;
		System.out.print("Directory of the exe: ");
		dir = sc.nextLine();
		dirSplit = dir.split("\\\\");
		dir = "";
		for (int i = 0; i < dirSplit.length; i++) {
			dir = dir + dirSplit[i] + "\\\\";
		}
		dir = dir.substring(0, dir.length() - 2);
		sc.close();
		return dir;	}

}
