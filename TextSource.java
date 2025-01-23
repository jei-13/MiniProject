import java.util.Random;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextSource {
    private Vector<String> v = new Vector<String>(); //단어 저장할 백터
    
    public TextSource() {
        try {
            loadWords("d:\\words.txt"); //파일 불러오기
        } catch (IOException e) {
        	return;
        }
    }

    //파일에서 단어 읽어서 백터에 추가
    private void loadWords(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String str;
            while ((str = reader.readLine()) != null) {
                v.add(str.trim()); //공백 제거 후 추가
            }
        }
    }

    public String get() {
        int index = (int) (Math.random() * v.size()); //단어 랜덤 선택
        return v.get(index);
    }

    public void add(String word) {
        v.add(word);
    }
}
