# The JavaScript MIME Type

 When sending JavaScript content, you should use text/javascript as per RFC 9239.

## Aliases

	application/javascript, application/x-javascript, text/javascript1.0, text/javascript1.1, text/javascript1.2, text/javascript1.3, text/javascript1.4, text/javascript1.5, text/jscript, and text/livescript are deprecated aliases for it. If you are writing a tool which consumes JavaScript (e.g. an HTTP client) then you should consider supporting them for backwards compatibility.

## History
	The text/javascript MIME type was used by convention until RFC 4329 attempted to replace it with application/javascript.