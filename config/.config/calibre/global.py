# calibre wide preferences

### Begin group: DEFAULT
 
# database path
# Percorso del database in cui sono salvati i libri
database_path = '/home/luca/library1.db'
 
# filename pattern
# Modelli per stimare i metadati dai nomi dei file
filename_pattern = u'(?P<title>.+) - (?P<author>[^_]+)'
 
# isbndb com key
# Chiave di accesso per isbndb.com
isbndb_com_key = ''
 
# network timeout
# Timeout predefinito per le operazioni di rete (secondi)
network_timeout = 5
 
# library path
# Percorso alla cartella in cui è salvata la biblioteca
library_path = u'/home/luca/calibre'
 
# language
# La lingua in cui visualizzare l'interfaccia utente
language = 'it'
 
# output format
# Il formato predefinito per la conversione degli ebook.
output_format = 'epub'
 
# input format order
# Lista ordinata di preferenze peri formati di input
input_format_order = cPickle.loads('\x80\x02]q\x01(U\x04EPUBq\x02U\x04AZW3q\x03U\x04MOBIq\x04U\x03LITq\x05U\x03PRCq\x06U\x03FB2q\x07U\x04HTMLq\x08U\x03HTMq\tU\x04XHTMq\nU\x05SHTMLq\x0bU\x05XHTMLq\x0cU\x03ZIPq\rU\x04DOCXq\x0eU\x03ODTq\x0fU\x03RTFq\x10U\x03PDFq\x11U\x03TXTq\x12e.')
 
# read file metadata
# Leggi metadati dai file
read_file_metadata = True
 
# worker process priority
# La priorità dei processi in corso. Una priorità alta significa che i processi sono eseguiti più velocemente e consumano più risorse. La maggioranza delle operazioni come conversioni/scaricamento notizie/aggiunta libri/ecc. sono influenzati da questa impostazione.
worker_process_priority = 'normal'
 
# swap author names
# Inverti nome e cognome dell'autore durante la lettura dei metadati
swap_author_names = False
 
# add formats to existing
# Aggiungi nuovi formati ai libri esistenti
add_formats_to_existing = False
 
# check for dupes on ctl
# Controlla i duplicati quando copi in un'altra biblioteca
check_for_dupes_on_ctl = False
 
# installation uuid
# Installation UUID
installation_uuid = 'afe9048c-1cac-4605-aa9f-f5da6ccec5fe'
 
# new book tags
# Tag da applicare ai libri aggiunti alla biblioteca
new_book_tags = cPickle.loads('\x80\x02]q\x01.')
 
# mark new books
# Mark newly added books. The mark is a temporary mark that is automatically removed when calibre is restarted.
mark_new_books = False
 
# saved searches
# Elenco di ricerche salvate con nome
saved_searches = cPickle.loads('\x80\x02}q\x01.')
 
# user categories
# Categorie del navigatore dei tag create dall'utente
user_categories = cPickle.loads('\x80\x02}q\x01.')
 
# manage device metadata
# Come e quando calibre aggiorna i metadati nel dispositivo.
manage_device_metadata = 'manual'
 
# limit search columns
# When searching for text without using lookup prefixes, as for example, Red instead of title:Red, limit the columns searched to those named below.
limit_search_columns = False
 
# limit search columns to
# Choose columns to be searched when not using prefixes, as for example, when searching for Red instead of title:Red. Enter a list of search/lookup names separated by commas. Only takes effect if you set the option to limit search columns above.
limit_search_columns_to = cPickle.loads('\x80\x02]q\x01(U\x05titleq\x02U\x07authorsq\x03U\x04tagsq\x04U\x06seriesq\x05U\tpublisherq\x06e.')
 
# use primary find in search
# Characters typed in the search box will match their accented versions, based on the language you have chosen for the calibre interface. For example, in  English, searching for n will match ñ and n, but if your language is Spanish it will only match n. Note that this is much slower than a simple search on very large libraries. Note that this option will have no effect if you turn on case-sensitive searching
use_primary_find_in_search = True
 
# case sensitive
# Make searches case-sensitive
case_sensitive = False
 
# migrated
# For Internal use. Don't modify.
migrated = False
 


