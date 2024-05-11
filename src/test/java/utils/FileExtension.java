package utils;

public enum FileExtension {

    TXT(".txt", "text/plain");

    private final String format;
    private final String mimeType;

    FileExtension(final String format, final String mimeType) {
        this.format = format;
        this.mimeType = mimeType;
    }

    public String getFormat() {
        return format;
    }

    public String getMimeType() {
        return mimeType;
    }
}
