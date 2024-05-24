import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //读入文件并转化为标准形式，存入graph中
        Read read_func = new Read();
        read_func.readFileToWords();
        read_func.readWordsToGraph();
        DGraph graph = read_func.graph;
        graph.Show();
        //分支选择由此开始
        Scanner scanner = new Scanner(System.in);   //创建Scanner对象
        int choice = 0;
        while (choice != 5) {
            // 显示菜单选项
            System.out.println("请选择要执行的操作:");
            System.out.println("1. 查询桥接词");
            System.out.println("2. 生成新文本");
            System.out.println("3. 随机游走");
            System.out.println("4. 计算最短路径");
            System.out.println("5. 退出程序");

            // 读取用户输入的选项
            while (!scanner.hasNextInt()) {
                System.out.println("无效的输入,请输入一个整数!");
                scanner.next(); // 读取并丢弃无效输入
            }
            choice = scanner.nextInt();

            // 根据用户选择执行相应的函数
            switch (choice) {
                case 1:
                    // 调用 queryBridgeWords 函数
                    String word1 = getInputWord(scanner, "请输入第一个单词:");
                    String word2 = getInputWord(scanner, "请输入第二个单词:");
                    System.out.println(graph.queryBridgeWords(word1, word2));
                    break;
                case 2:
                    // 调用 generateNewText 函数
                    String inputText = getInputText(scanner, "请输入文本:");
                    System.out.println(graph.generateNewText(inputText));
                    break;
                case 3:
                    // 调用 randomWalk 函数
                    System.out.println(graph.randomWalk());
                    break;
                case 4:
                    // 调用 calcShortestPath 函数
                    String source = getInputWord(scanner, "请输入起点单词:");
                    String target = getInputWord(scanner, "请输入终点单词:");
                    System.out.println(graph.calcShortestPath(source, target));
                    break;
                case 5:
                    System.out.println("程序已退出。");
                    break;
                default:
                    System.out.println("无效的选项!");
                    break;
            }
        }
        // 关闭 Scanner 对象
        scanner.close();
    }

    // 获取用户输入的单词
    private static String getInputWord(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    // 获取用户输入的文本
    private static String getInputText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // 消耗换行符
        return scanner.nextLine();
    }

    // 获取用户输入的整数
    private static int getInputInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }


}