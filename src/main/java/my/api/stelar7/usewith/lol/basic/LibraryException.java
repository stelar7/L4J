package my.api.stelar7.usewith.lol.basic;

public class LibraryException extends Exception
{
    public enum Type
    {
        BAD_REQUEST,
        NOT_FOUND,
        PARSE_FAILURE,
        RATE_LIMIT,
        SERVER_ERROR,
        UNAUTHORIZED,
        UNAVAILABLE,
        UNKNOWN;
    }

    public static int lastError;
    Type              type;

    /**
     *
     * @param t
     *            The HTTP response status code to generate error from;
     */
    public LibraryException(final int code)
    {
        this.type = Type.UNKNOWN;
        if (code >= 500)
        {
            this.type = Type.SERVER_ERROR;
        }
        if (code == 404)
        {
            this.type = Type.NOT_FOUND;
        }
        if (code == 401)
        {
            this.type = Type.UNAUTHORIZED;
        }
        if (code == 400)
        {
            this.type = Type.BAD_REQUEST;
        }
        if (code == 429)
        {
            this.type = Type.RATE_LIMIT;
        }
    }

    /**
     *
     * @param t
     *            The type of {@link LibraryException} to throw
     */
    public LibraryException(final Type t)
    {
        this.type = t;
    }

    @Override
    public String getMessage()
    {
        switch (this.type)
        {
            case BAD_REQUEST:
                return "Bad Request";
            case NOT_FOUND:
                return "404 No data found";
            case PARSE_FAILURE:
                return "Failed to parse API response";
            case RATE_LIMIT:
                return "Rate limit reached";
            case SERVER_ERROR:
                return "Server Error";
            case UNAUTHORIZED:
                return "Unauthorized";
            case UNAVAILABLE:
                return "Service Unavailable";
            default:
                return "An unknown error occured";
        }
    }

}
