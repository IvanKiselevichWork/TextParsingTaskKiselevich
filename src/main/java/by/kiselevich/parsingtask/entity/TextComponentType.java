package by.kiselevich.parsingtask.entity;

public enum TextComponentType {
    TEXT("(?m)\t*.+\n*\t*"),
    PARAGRAPH("(?m)(?s)([^\\.!?]+)([\\.!?]+)\n*\t*"),
    SENTENCE("(?m)([^ ]+)( *)"),
    LEXEME("(?m)(\\W|\\D)*(\\w|\\d)+(\\W|\\D)*"),
    WORD("(?m)(.|\n)"),
    SYMBOL("");

    private final String regex;

    TextComponentType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}
