import java.io.*;
import java.util.Scanner;
public class Read {
    public void readFileToWords(String inputName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文件名：");
        //fileName = scanner.nextLine();
        fileName = "test1.txt";
        //System.out.println("输入的文件名为：" + fileName);

        fileName = inputName;
        File file = new File(fileName);
        BufferedReader reader = null;
        //删除先前文件
        String resultfilename = "Result_"+fileName.substring(0, fileName.length()-4);
        File resultFile = new File(resultfilename);
        resultFile.delete();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String s = tempString.replaceAll("[\\p{Punct}]+", " "); //将字符串中所有标点替换为空格
                s = s.replaceAll("\\d", ""); // 将字符串中的数字替换为空
                String[] Words = s.trim().split("\\s+");    //字符串按空格、换行等空白字符分割成字符串数组，变相替换换行符

                for (int i = 0; i < Words.length; i++) {
                    Words[i] = Words[i].toLowerCase();
                }

                for (String str : Words) {
                    if (!str.isEmpty()) {
                        write("Result_" + fileName.substring(0, fileName.length() - 4), str + "\r\n");
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

/*    public void readFileToWords(String inputName) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文件名：");
        //fileName = scanner.nextLine();
        fileName = "test1.txt";
        //System.out.println("输入的文件名为：" + fileName);

        fileName = inputName;
        File file = new File(fileName);
        BufferedReader reader = null;
        //删除先前文件
        String resultfilename = "Result_"+fileName.substring(0, fileName.length()-4);
        File resultFile = new File(resultfilename);
        resultFile.delete();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String s = tempString.replaceAll("[\\p{Punct}]+", " ");
                //System.out.println(s);
                String[] Words = s.trim().split("\\s+");

                for (int i = 0; i < Words.length; i++)
                {
                    Pattern p = Pattern.compile("a-z||A-Z");
                    Matcher m = p.matcher(Words[i]);
                    Words[i] = m.replaceAll("").trim().toLowerCase();
                }
                //System.out.println("line"+line+":"+tempString);
                for (String str : Words) {
                    write("Result_"+fileName.substring(0, fileName.length()-4), str + "\r\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }*/

    public void readWordsToGraph() {
        graph = new Dgraph();
        resultsFileName = "Result_"+fileName.substring(0, fileName.length()-4);
        File file = new File(resultsFileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            String word1 = null;
            String word2 = null;
            while ((tempString = reader.readLine()) != null) {
                word2 = tempString;
                if (word1 != null) {
                    graph.addEdge(word1, word2);
                }
                word1 = tempString;
                //System.out.println(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    static void write(String fileName,String str){
        try{
            FileWriter writer = new FileWriter(fileName,true);
            writer.write(str);;
            //System.out.println("WRITE:str");
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static String fileName = null;
    private static String resultsFileName = null;
    public Dgraph graph ;
}