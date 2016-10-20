# preferences for the calibre GUI

### Begin group: DEFAULT
 
# send to storage card by default
# Invia il file alla scheda di memoria invece che alla memoria principale come impostazione predefinita
send_to_storage_card_by_default = False
 
# confirm delete
# Chiedi conferma prima di eliminare
confirm_delete = False
 
# main window geometry
# Geometria della finestra principale
main_window_geometry = cPickle.loads('\x80\x02csip\n_unpickle_type\nq\x01U\x0cPyQt5.QtCoreq\x02U\nQByteArrayU2\x01\xd9\xd0\xcb\x00\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x05U\x00\x00\x02\xec\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x05U\x00\x00\x02\xec\x00\x00\x00\x00\x00\x00\x00\x00\x05V\x85\x87Rq\x03.')
 
# new version notification
# Avverti quando è disponibile una nuova versione
new_version_notification = True
 
# use roman numerals for series number
# Usa numeri romani per i numeri delle serie
use_roman_numerals_for_series_number = True
 
# sort tags by
# Ordina l'elenco dei tag per nome, popolarità o valutazione
sort_tags_by = 'name'
 
# match tags type
# Corrispondenza dei tag per alcuni o per tutti
match_tags_type = 'any'
 
# cover flow queue length
# Numero di copertine da visualizzare nella modalità di navigazione delle copertine
cover_flow_queue_length = 6
 
# LRF conversion defaults
# Parametri predefiniti per la conversione in LRF
LRF_conversion_defaults = cPickle.loads('\x80\x02]q\x01.')
 
# LRF ebook viewer options
# Opzioni del visore di libri LRF
LRF_ebook_viewer_options = None
 
# internally viewed formats
# Formati visualizzati utilizzando il visore interno
internally_viewed_formats = cPickle.loads('\x80\x02]q\x01(U\x03LRFq\x02U\x04EPUBq\x03U\x03LITq\x04U\x04MOBIq\x05U\x03PRCq\x06U\x04POBIq\x07U\x03AZWq\x08U\x04AZW3q\tU\x04HTMLq\nU\x03FB2q\x0bU\x03PDBq\x0cU\x02RBq\rU\x03SNBq\x0eU\x05HTMLZq\x0fU\x05KEPUBq\x10e.')
 
# column map
# Colonne da mostrare nella lista dei libri
column_map = cPickle.loads('\x80\x02]q\x01(U\x05titleq\x02U\x08ondeviceq\x03U\x07authorsq\x04U\x04sizeq\x05U\ttimestampq\x06U\x06ratingq\x07U\tpublisherq\x08U\x04tagsq\tU\x06seriesq\nU\x07pubdateq\x0be.')
 
# autolaunch server
# Avvia automaticamente il server dei contenuti quando si apre l'applicazione
autolaunch_server = False
 
# oldest news
# Notizie più vecchie da mantenere nel database
oldest_news = 10
 
# systray icon
# Mostra l'icona nell'area di notifica
systray_icon = False
 
# upload news to device
# Invia le notizie scaricate al dispositivo
upload_news_to_device = True
 
# delete news from library on upload
# Elimina i libri dalla biblioteca dopo l'invio al dispositivo
delete_news_from_library_on_upload = False
 
# separate cover flow
# Visualizza le copertine in una finestra separata invece che nella finestra principale di calibre.
separate_cover_flow = False
 
# disable tray notification
# Disabilita messaggi dall'icona nella area di notifica
disable_tray_notification = False
 
# default send to device action
# Azione predefinita da eseguire quando viene fatto clic sul pulsante di invio al dispositivo.
default_send_to_device_action = 'DeviceAction:main::False:False'
 
# asked library thing password
# Asked library thing password at least once.
asked_library_thing_password = False
 
# search as you type
# Inizia a cercare mentre scrivi. Se questa opzione è disabilitata, la ricerca inizierà solo dopo aver premuto Return o Invio.
search_as_you_type = False
 
# highlight search matches
# Durante la ricerca, mostra tutti i libri con i risultati evidenziati piuttosto che mostrare solo le occorrenze trovate. Puoi usare il tasto N o F3 per andare sulla prossima occorrenza.
highlight_search_matches = False
 
# save to disk template history
# Previously used Save to Disk templates
save_to_disk_template_history = cPickle.loads('\x80\x02]q\x01.')
 
# send to device template history
# Previously used Send to Device templates
send_to_device_template_history = cPickle.loads('\x80\x02]q\x01.')
 
# main search history
# Search history for the main GUI
main_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# viewer search history
# Search history for the ebook viewer
viewer_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# viewer toc search history
# Search history for the ToC in the ebook viewer
viewer_toc_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# lrf viewer search history
# Search history for the LRF viewer
lrf_viewer_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# scheduler search history
# Search history for the recipe scheduler
scheduler_search_history = cPickle.loads('\x80\x02]q\x01(X\x0e\x00\x00\x00new york timesq\x02X\n\x00\x00\x00repubblicaq\x03X\x0e\x00\x00\x00disinformaticoq\x04X\x13\x00\x00\x00corriere della seraq\x05e.')
 
# plugin search history
# Search history for the plugin preferences
plugin_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# shortcuts search history
# Search history for the keyboard preferences
shortcuts_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# jobs search history
# Search history for the tweaks preferences
jobs_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# tweaks search history
# Search history for tweaks
tweaks_search_history = cPickle.loads('\x80\x02]q\x01.')
 
# worker limit
# Numero massimo di lavori di conversione/scaricamento di notizie. Il numero è il doppio del valore attuale per ragioni storiche.
worker_limit = 6
 
# get social metadata
# Scarica metadati sociali (tag/valutazioni/etc.)
get_social_metadata = True
 
# overwrite author title metadata
# Usa i nuovi metadati per cambiare l'autore e il titolo
overwrite_author_title_metadata = True
 
# auto download cover
# Scarica automaticamente la copertina, se disponibile
auto_download_cover = False
 
# enforce cpu limit
# Limita il massimo numero di lavori simultanei al numero delle CPU.
enforce_cpu_limit = True
 
# gui layout
# La disposizione dell'interfaccia utente. Larga ha il pannello con i dettagli del libro sulla destra, mentre se è stretta è in basso.
gui_layout = 'wide'
 
# show avg rating
# Visualizza la valutazione media per elemento nel navigatore dei tag
show_avg_rating = True
 
# disable animations
# Disattiva animazioni interfaccia
disable_animations = False
 
# tag browser hidden categories
# categorie del navigatore dei tag da non visualizzare
tag_browser_hidden_categories = cPickle.loads('\x80\x02c__builtin__\nset\nq\x01]\x85Rq\x02.')
 


