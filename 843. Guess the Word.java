// Beat 100% in time //
// The idea is, we first take a random guess (let's call it X) from the candidate list(initially it contains all the words)
// Then we know how many chars (let's call it matchScore) are matched for this randome guessed word X with the secret word
// Then we create a new candidate list in which all the words have to have same matchScore with X in order to be a secret word
// We keep doing this to reduce the candidate word list. 

// Here I found if I take one randome guess each time, some test cases will fail. So I did TWO guess each time, to reduce the candidate list faster. Then it get AC.

    public void findSecretWord(String[] wordlist, Master master) {
        
        List<String> candidateList = new ArrayList<>();
        Random rand = new Random();
        
        for (String word : wordlist) {
            candidateList.add(word);
        }
        
        for (int i = 0; i < 5; i ++) {
            int len = candidateList.size();
            String nextGuess = candidateList.get(rand.nextInt(len));
            String nextGuess2 = candidateList.get(rand.nextInt(len));
            
            int score = master.guess(nextGuess);
            int score2 = master.guess(nextGuess2);
            
            if (score == 6 || score2 == 6) {
                break;
            }
            
            List<String> nextList = new ArrayList<>();
            for (String word: candidateList) {
                if (match(word, nextGuess) == score && match(word, nextGuess2) == score2) {
                    nextList.add(word);
                }
            }
            candidateList = nextList;
        }
    }
    
    private int match(String s1, String s2) {
        
        int score = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                score ++;
            }
        }
        return score;
    }
}
