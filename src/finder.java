import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class finder {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean isRoot = false;
        String root = null;
        String[] suffixCodes = new String[0];
        String[] prefixCodes = new String[0];
        System.out.println("Enter a word: ");
        String word = null;
        while (!isRoot) {
            word = s.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader("datasets/root.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (word.contains(line.toLowerCase())) {
                        System.out.println("Root Word: "+ line);
                        root = line.toLowerCase();
                        isRoot = true;
                    }
                }
                if (!isRoot) System.out.println("Please enter a root word: ");
            }
        catch(IOException e){
            e.printStackTrace();
        }
    }
        ArrayList<String> dictArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("datasets/dict.txt"))) {
            String fileword;
            while((fileword = reader.readLine()) != null){
                if(fileword.contains(root)){
                    dictArray.add(fileword);
                }
            }
            dictArray.remove(root);
            BufferedReader prefixReader = new BufferedReader(new FileReader("datasets/prefix.txt"));
            prefixCodes = prefixReader.readLine().split(" ");
            BufferedReader suffixReader = new BufferedReader(new FileReader("datasets/suffix.txt"));
            suffixCodes = suffixReader.readLine().split(" ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        prefixCodes, suffixCodes and dictArray
//        Check for prefixes
        ArrayList<String> prefixWords = new ArrayList<>();
        ArrayList<String> suffixWords = new ArrayList<>();
        ArrayList<String> mixedWords = new ArrayList<>();
        System.out.println("\n=================================\n");
        System.out.println("Pure Prefixes are :");
        for (int i = 0; i < prefixCodes.length;  i++) {
            if(dictArray.contains(prefixCodes[i]+root)){
                System.out.println(prefixCodes[i]+root);
                prefixWords.add(prefixCodes[i]+root);
            }else{
                mixedWords.add(dictArray.get(i));
            }
        }
//       Check for suffixes
        System.out.println("\n=================================\n");
        System.out.println("Pure Suffixes are :");
        for (int i = 0; i < suffixCodes.length;  i++) {
            String temp = root + suffixCodes[i];
            if(dictArray.contains(temp)){
                System.out.println(temp);
                suffixWords.add(temp);
                if(mixedWords.contains(temp)){
                    mixedWords.remove(temp);
                }
            } else{
                if(!prefixWords.contains(dictArray.get(i)))
                    if(!mixedWords.contains(dictArray.get(i)))
                        mixedWords.add(dictArray.get(i));
            }
        }
        System.out.println("\n=================================\n");
        System.out.println("Mixed words are: ");
        for (int i = 0; i < mixedWords.size(); i++) {
            System.out.println(mixedWords.get(i));
        }
    }
}