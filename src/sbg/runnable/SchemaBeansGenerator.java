package sbg.runnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class SchemaBeansGenerator {

	//
	// CHANGE THESE FIELDS TO MATCH YOUR MACHINE/SETUP
	//
	
	// The full path to the directory where generated files will be placed
	private static final String TARGET_DIRECTORY = "C:/Users/brandon.duffy/Desktop/jaxb_output";
	
	// The package declaration that will be put at the top of the generated files
	private static final String PACKAGE_DECLARATION = "com.stillwaterinsurance.auto.generated.wse";
	
	private static final String SCHEMA_BASE = 
			"C:/Users/brandon.duffy/projects/affiniti/fidelity_apps/XMLEngine/WebServiceEngine/src/schemas/";
	
	// The full path to the .xsd file
	private static final String XSD_PATH = SCHEMA_BASE + "FidelityAcord.xsd";
	
	// Complete paths to any external bindings files
	private static final String[] BINDINGS_PATHS = new String[]{SCHEMA_BASE + "wse-bindings-jaxb.xml"};
	
	// Complete path to the jdk to run xjc from
	private static final String JDK_DIRECTORY = "C:/Program Files/Java/jdk1.8.0_65";
	
	
	public static void main(String[] args) throws Exception
	{
		// Make the directory if it doesn't exist
		File file = new File(TARGET_DIRECTORY);
		file.mkdir();
		
		// Build the jaxb command
		StringBuilder cmd = new StringBuilder();
		cmd.append(JDK_DIRECTORY).append("/bin/xjc.exe -no-header ")
		.append(XSD_PATH)
		.append(" -d ").append("\"").append(TARGET_DIRECTORY).append("\"");
		cmd.append(" -p ").append(PACKAGE_DECLARATION);
		for (String binding : BINDINGS_PATHS)
		{
			cmd.append(" -b ").append(binding);
		}
		
		// Execute the command
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(cmd.toString());
		
		// Print output of command to console
		BufferedReader br = new BufferedReader(new InputStreamReader((p.getInputStream())));
		String line;
		while ((line = br.readLine()) != null)
		{
			System.out.println(line);
		}
	}
	
}
