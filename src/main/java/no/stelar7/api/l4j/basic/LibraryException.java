package no.stelar7.api.l4j.basic;

import lombok.Getter;

public class LibraryException extends Exception
{
    public enum Type
    {
        BAD_REQUEST,
        NOT_FOUND,
        PARSE_FAILURE,
        RATE_LIMIT,
        SERVER_ERROR,
        TIMEOUT,
        UNAUTHORIZED,
        UNAVAILABLE,
        UNKNOWN;
    }

    @Getter
    Type type;
    @Getter
    int  retryAfter;

    /**
     *
     * @param t
     *            The HTTP response status code to generate error from;
     */
    public LibraryException(final int code, final int retryafter)
    {
        this.retryAfter = retryafter;
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
        if (code == 0)
        {
            this.type = Type.TIMEOUT;
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
        String error = "";
        switch (this.type)
        {
            case BAD_REQUEST:
                error += "Bad Request";
                break;
            case NOT_FOUND:
                error += "No data found";
                break;
            case PARSE_FAILURE:
                error += "Failed to parse API response";
                break;
            case RATE_LIMIT:
                error += "Rate limit reached";
                break;
            case SERVER_ERROR:
                error += "Server Error";
                break;
            case UNAUTHORIZED:
                error += "Unauthorized";
                break;
            case UNAVAILABLE:
                error += "Service Unavailable";
                break;
            default:
                error += "An unknown error occured";
        }
        return "(" + this.type + ") " + error;
    }

}
