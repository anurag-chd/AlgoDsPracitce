import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CleanUpSizeFields {


	public static void main(String[] args){
		//StringBuilder resultStringBuilder = new StringBuilder();
		List<String> lines = new ArrayList<String>();
		int[] indexArr = {20,22,24,26,29,30,33,34,36,38,39,40,42,44,45,46};//,72,74,75,76,78,80,81,82};
		String line = null;
		String erunAxeFields = ",,,,,,,,,,,,";
		try {
			File f1 = new File("/Users/achoudhary47/filq_repo/AlgoDSPracitce/src/main/resources/expected_output_template_2.csv");
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] words = line.split(",",-1);
				for(int index : indexArr){
					words[index] = "";
				}
				/*StringBuilder sbr = new StringBuilder();
				for(int i = 0 ; i < words.length; i++){
					sbr.append(words[i]);
					if(i != words.length-1)
						sbr.append(",");
				}*/
				StringJoiner joiner = new StringJoiner(",");
				for(String s : words ){
					joiner.add(s);
				}

				//lines.add(sbr.toString() );
				lines.add(joiner.toString()+erunAxeFields);
			}
			fr.close();
			br.close();


			File f2 = new File("/Users/achoudhary47/filq_repo/AlgoDSPracitce/src/main/resources/expected_output_template_2_out.csv");
			FileWriter fw = new FileWriter(f2);
			BufferedWriter out = new BufferedWriter(fw);

			for(String s : lines){
				out.write(s);
				out.newLine();
			}

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
