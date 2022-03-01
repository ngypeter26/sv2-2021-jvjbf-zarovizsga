package sentences;

public class SentenceTransformer {
    public String shortenSentence(String sentence){
        if (! startsWithCapitalLetter(sentence)){
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (! endWithMark(sentence)){
            throw new IllegalArgumentException("Must end with . ! or ?");
        }

        if (numberOfWords(sentence)>=5){
            return getShorterVersion(sentence);
        }else{
            return sentence;
        }
    }


    public boolean startsWithCapitalLetter(String sentence){
        return sentence.charAt(0) ==sentence.toUpperCase().charAt(0);
    }
    public boolean endWithMark(String sentence){
        String lastLetter = sentence.substring(sentence.length()-1);
        return ".".equals(lastLetter) || "!".equals(lastLetter)|| "?".equals(lastLetter);
    }

    public int numberOfWords(String sentence){
        String[] parts = sentence.split(" ");
        return parts.length;
    }

    public String getShorterVersion(String sentence){
        String[] parts = sentence.split(" ");
        return parts[0] + " ... " + parts[parts.length-1];
    }

}
