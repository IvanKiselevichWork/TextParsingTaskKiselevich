package by.kiselevich.parsingtask.entity;

public enum TextComponentType {
    TEXT("(?m)(.+)(\n*\t*)"),
    PARAGRAPH("(?m)([^\\.!?]+)([\\.!?]+)"),
    SENTENCE("(?m)([^ ]+)( *)"),
    LEXEME("(?m)([\\w\\d]+)(\\W*)"),
    WORD("(?m)(\\w+?)(\\W*)"),
    SYMBOL("");

    private final String regex;

    TextComponentType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}
