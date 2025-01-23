import java.io.*;
import java.util.*;

public class RankSource { //이름과 점수 txt파일에 저장
    private File file;

    public RankSource(String filePath) {
        file = new File(filePath);
        try {
            if (!file.exists()) { //파일 없으면 생성
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //이름,점수 파일에 저장
    public void saveScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(name + "," + score); //이름,점수 로 입력
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //파일에서 점수 데이터 읽어서 리스트에 저장
    public ArrayList<String[]> loadScores() {
        ArrayList<String[]> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) { //한줄씩 읽기
                String[] parts = line.split(","); //, 기준으로 분리해서 배열에 저장
                if (parts.length == 2) {
                    scores.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    //리스트 정렬
    public ArrayList<String[]> getSortedScores() {
        ArrayList<String[]> scores = loadScores();
        scores.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        return scores;
    }
}
