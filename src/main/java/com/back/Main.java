package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        Scanner sc = new Scanner(System.in);

        // 등록 번호
        int id = 0;

        // 입력된 명언 리스트
        List<WiseSaying> wiseSayingList = new ArrayList<>();

        // 종료하기 전까지 반복
        while (true) {
            // 명령어 입력
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim(); // 양옆 공백 제거

            // 종료 조건
            if (cmd.equals("종료")) {
                break;
            }

            if (cmd.equals("등록")) {
                // 명언, 작가 입력
                System.out.print("명언 : ");
                String content = sc.nextLine().trim();

                System.out.print("작가 : ");
                String author = sc.nextLine().trim();

                // 등록
                id++;
                wiseSayingList.add(new WiseSaying(id, content, author));
                System.out.println(id + "번 명언이 등록되었습니다.");
            }

            if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                wiseSayingList.sort((q1, q2) -> q2.id - q1.id);
                for (WiseSaying wiseSaying : wiseSayingList) {
                    System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
                }
            }

            if (cmd.startsWith("삭제?id=")) {
                // id 파악
                int targetId = Integer.parseInt(cmd.substring(6));

                boolean isDeleted = false;

                // 해당 id에 해당하는 리스트 원소가 있으면 삭제
                for (int i = wiseSayingList.size() - 1; i >= 0; i--) { // 삭제되면 리스트 뒤의 요소들이 앞으로 당겨지므로 뒤에서부터 순회
                    if (wiseSayingList.get(i).id == targetId) {
                        wiseSayingList.remove(i);
                        isDeleted = true;
                        break;
                    }
                }

                if (isDeleted) {
                    System.out.println(targetId + "번 명언이 삭제되었습니다.");
                } else {
                    // 리스트가 빈 값이라도 예외 처리
                    System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                }
            }

            if (cmd.startsWith("수정?id=")) {
                // 탐색 여부
                boolean isFound = false;
                // id 파악
                int targetId = Integer.parseInt(cmd.substring(6));
                // 해당 id에 해당하는 리스트 원소가 있으면 수정
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).id == targetId) {
                        isFound = true;
                        WiseSaying wiseSaying = wiseSayingList.get(i);
                        System.out.println("명언(기존): " + wiseSaying.content);
                        System.out.print("명언 : ");
                        String newContent = sc.nextLine();
                        wiseSaying.content = newContent;

                        System.out.println("작가(기존): " + wiseSaying.author);
                        System.out.print("작가 : ");
                        String newAuthor = sc.nextLine();
                        wiseSaying.author = newAuthor;
                    } else {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    }
                }
                if (!isFound) {
                    System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                }
            }
        }
    }
}

class WiseSaying {
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}
