"                      === global options ===
set nocompatible
syntax on
set ai
set backspace=indent,eol,start
set history=50
set showcmd
set incsearch
set hlsearch
set number
set cindent
set ignorecase
set smartcase
set autowrite
set showbreak=\|------>
"set breakindent    " TODO: to change
set fileformat=unix
set encoding=utf-8
set noequalalways
set wildmenu
set wildmode=longest:full,full
set wildignore+=*.d,*.o
set modelineexpr

set noexpandtab
set tabstop     =4
set softtabstop =4
set shiftwidth  =4
set scrolloff   =4
set cino=:0,+0,(2,J0,{1,}0,>4,)1,m2

"set cursorline "too slow!
set nocursorline
set lazyredraw

set foldenable
set foldmethod=manual
set foldlevel=1
set foldcolumn=0

"sublime text-like indentation lines
set listchars=tab:\|\  "<--- there is a space here
"set list


set backupdir=~/.vim_backup//,.
set directory=~/.vim_backup//,.

set cscopequickfix=s-,c-,d-,i-,t-,e-,a-

set guioptions-=m
set guioptions-=T
set guioptions-=r

set guicursor+=a:blinkon0

set guifont=Office\ Code\ Pro

filetype plugin indent on

let &t_SI = "\<Esc>[3 q"
let &t_SR = "\<Esc>[1 q"
let &t_EI = "\<Esc>[1 q"

let &t_ut=''

" ===

"                       === movement macros ===

nnoremap h <C-w>h
nnoremap j <C-w>j
nnoremap k <C-w>k
nnoremap l <C-w>l

"change tabs
nnoremap n gT
nnoremap p gt

"nmap - /
"nmap _ ?
"vmap - /
"vmap _ ?
"nmap c- c/

set foldcolumn=1


"move to next/previous function
map [[ ?{<CR>w99[{
map ][ /}<CR>b99]}
map ]] j0[[%/{<CR>
map [] k$][%?}<CR>

nnoremap H g^
nnoremap L g$
vnoremap H g^
vnoremap L g$

nnoremap K {
nnoremap J }
vnoremap K {
vnoremap J }

nnoremap ; g;

nnoremap j gj
nnoremap k gk

map <ScrollWheelUp> <C-Y><C-Y>
map <ScrollWheelDown> <C-E><C-E>

"nnoremap <silent>   :exe "tabn ".g:lasttab<cr> "breaks if pressing esc two times

"===

"                   === editing macros ===

inoremap jk <Esc>
inoremap kj <Esc>

inoremap JK <Esc>

nnoremap <space> za

nmap p ]p
nmap P [p

nnoremap ,p "+]p
nnoremap ,P "+]P


nnoremap U <C-R>
nnoremap ,s :w<CR>
nnoremap ,q :wq<CR>
nnoremap ,Q :q!<CR>

map <F12> :set invpaste<CR>

" ===

"                   === command macros ===

cmap w!! w !sudo tee > /dev/null %

nnoremap ,w :w<CR>

nnoremap gV `[v`]

vnoremap Y "+y
nnoremap Y "+y
nnoremap YY "+yy

"remove whitespace
"nnoremap ,W :%s/\s\+$//<cr>:let @/=''<CR>

iab <expr> dts strftime("%c")
iab <expr> todo "// TODO ".strftime('%Y/%m/%d %H:%M')." - "

nnoremap ,v :tabnew $MYVIMRC<CR>

"execute command
nnoremap ,p 0ik:r!<ESC>"cdd@c 

"ctags
nnoremap <C-\> <C-w>}
"cscope
nnoremap ,u yiw:cs find s <C-R>0<CR>:copen<CR>

nnoremap <c-l> :nohl<cr><c-l>

"close quickfix window
nnoremap ,x :ccl<CR>
nmap q :ccl<CR>

"record macro
nnoremap Q q

"surround with \b{}
vnoremap ,b "9da\b{}<ESC>Pll
vnoremap ,v "9da\v{}<ESC>Pll
vnoremap ,t "9da\t{}<ESC>Pll

"join lines
vnoremap ,j :join<CR>

nnoremap ,S :!clear && shellcheck %<CR>

" ===

"                   === auto commands ===

"auto reload vim_rc
augroup reload_vimrc 
    autocmd!
    autocmd BufWritePost $MYVIMRC source $MYVIMRC
augroup END 

"keep clipboard on exit + set cursor on start
augroup my_clipboard
	autocmd!
	autocmd VimLeave * call system("xsel -ib", getreg('+'))
	autocmd VimEnter * silent !echo -ne "\e[1 q"
augroup END

"to switch to last active tab
augroup my_tab
	autocmd!
	au TabLeave * let g:lasttab = tabpagenr()
augroup END

"auto reload folds
"autocmd BufWinLeave *.* mkview!
"autocmd BufWinEnter *.* silent loadview

" ===

"                   === plugin list ===

call plug#begin('~/.vim/bundle')
Plug 'godlygeek/csapprox'
Plug 'flazz/vim-colorschemes'
Plug 'altercation/vim-colors-solarized'
Plug 'scrooloose/nerdtree'
Plug 'majutsushi/tagbar'
Plug 'ervandew/supertab'
Plug 'godlygeek/tabular'
Plug 'rhysd/clever-f.vim'
Plug 'scrooloose/nerdcommenter'
Plug 'EinfachToll/DidYouMean'
Plug 'Konfekt/FastFold'
Plug 'matze/vim-move'
Plug 'junegunn/limelight.vim'
Plug 'junegunn/goyo.vim'
Plug 'junegunn/vim-peekaboo'			"display register values
Plug 'xuhdev/vim-latex-live-preview'

"Plug 'tikhomirov/vim-glsl'				"enables syntax highlight in shader files

" ---- experimental
Plug 'kshenoy/vim-signature'			"display marks
"Plug 'tpope/vim-obsession'
Plug 'dag/vim-fish'						"support for fish file editing
"Plug 'airblade/vim-gitgutter'
"Plug 'severin-lemaignan/vim-minimap'
Plug 'jeaye/color_coded'                " C highlighting (somewhat heavy!)

"Plug 'artur-shaik/vim-javacomplete2'



"Plug 'airblade/vim-rooter'
"Plug 'takac/vim-hardtime'
"Plug 'mileszs/ack.vim'
"Plug 'jnurmine/Zenburn'
"Plug 'junegunn/vim-slash'

"Plug 'KeitaNakamura/tex-conceal.vim', {'for': 'tex'}

"Plug 'junegunn/fzf', { 'dir': '~/.fzf', 'do': './install --all' }
Plug 'junegunn/fzf.vim'

"Plug 'vim-airline/vim-airline'
"Plug 'vim-airline/vim-airline-themes'
"
"Plug 'xolox/vim-misc'

"Plug 'ctrlpvim/ctrlp.vim'
"Plug 'chrisbra/histwin.vim'
"Plug 'metakirby5/codi.vim'				"real time python preview
"Plug 'xolox/vim-colorscheme-switcher'
"Plug 'wkentaro/conque.vim'				"shell inside a buffer
"Plug 'vim-scripts/Gundo'
"Plug 'scrooloose/syntastic'
"Plug 'artur-shaik/vim-javacomplete2'
"Plug 'mbbill/undotree'
"Plug 'itchyny/lightline.vim'
"Plug 'tmhedberg/SimpylFold'
"Plug 'vim-scripts/indentpython.vim'
"Plug 'hdima/python-syntax'
Plug 'markonm/traces.vim'				"real time preview of substitutions

Plug 'wsdjeg/FlyGrep.vim'
Plug 'powerman/vim-plugin-AnsiEsc'      "type :AnsiEsc to hide color escapes

call plug#end()

" ===

"                   === plugin options ===

let g:airline_theme = "zenburn"
let g:move_key_modifier = 'C' "vim-move keybinding
let g:JavaComplete_SourcesPath = "~/progetti/silvestri/java-project/gapp"
let g:syntastic_mode_map = {"mode": "passive","active_filetypes": [],"passive_filetypes":[]}
let g:ctrlp_cmd = 'CtrlPMRUFiles'
let g:fzf_action = {'ctrl-t': 'tab split','ctrl-x': 'split','ctrl-v': 'vsplit' }
let g:ackprg = 'ag --vimgrep --smart-case --ignore-dir shlr -Q'
let g:ack_use_cword_for_empty_search = 1
let g:ack_autoclose = 1
let g:livepreview_previewer='zathura'
"let g:livepreview_engine='pdflatex --shell-escape '
let g:livepreview_engine='xelatex --shell-escape'
let g:limelight_conceal_ctermfg = '239'
let g:goyo_width = '93%'
let python_highlight_all=1
let g:FlyGrep_input_delay=200
let g:NERDAltDelims_c=1
let g:NERDAltDelims_java=1
let g:tagbar_sort=0
let g:solarized_termcolors=256 " to make it work in the terminal


  "supertab
set completeopt=menuone,preview
"hi SpecialKey ctermfg=236


  "javacomplete
autocmd FileType java setlocal omnifunc=javacomplete#Complete
nmap <F6> <Plug>(JavaComplete-Imports-AddMissing)

" ===

"                   === plugin macros ===

nnoremap ,n :NERDTreeToggle<Enter>
nnoremap ,t :TagbarToggle<Enter>

nnoremap ,T  : Tabularize /=<CR>
vnoremap ,T  : Tabularize /=<CR>
nnoremap ,,T : Tabularize /=/r1c1r0<CR>
vnoremap ,,T : Tabularize /=/r1c1r0<CR>

nnoremap ,g :GundoToggle<CR>

"nerdcommenter
nmap ,e  <Leader>c<Space>
vmap ,e <Leader>c<Space>
nmap ,cc <Leader>c<Space>
nmap ,cm <Leader>cm
nmap ,cs <Leader>cs
vmap ,cc <Leader>c<Space>
vmap ,cm <Leader>cm
vmap ,cs <Leader>cs
vmap ,C <Leader>c<Space>

nnoremap ,f "pyiw::Ag<Space><C-R>p<CR>
nnoremap ,,f "pyiw::Tags<Space><C-R>p<CR>
nnoremap ,F :Files<CR>


" ===

"                   === filetype settings ===

"			==== C ====
function! SettingsC() 
	""" ROBA PER RADARE "
	set cindent
	set tabstop=8
	set noexpandtab
	set shiftwidth=8
	set softtabstop=8
	set cino=:0,+0,(2,J0,{1,}0,>8,)1,m2
	nnoremap <F4> :wa <CR> :!g++ % -o comp_%:r ;  ./comp_%:r <CR>

	""" ROBA per barrelfish
	set tabstop=4
	set expandtab
	set shiftwidth=4
	set softtabstop=4
    setlocal foldnestmax=1

    nnoremap <F5> :wa <CR> :silent !killall qemu-system-arm -15; echo timeout 20 make -j qemu_a15ve_4 > build/fifo<CR> :redraw!<CR>

	""" for the gitgutter plugin
	set updatetime=600

	"set list
	"set listchars=space:.,tab:>-,trail:.,nbsp:.  
	"hi SpecialKey ctermfg=236
endfunction

"			==== C++ ====
function! SettingsCpp() 
	"the parentesis are needed to combine the output of the two commands
	set makeprg=(g++\ %\ -o\ comp_%:r\ -O2\ -g\ &&\ ./comp_%:r\ <\ input.txt)
	nnoremap <F4> :wa <CR> :silent make\|redraw!\|copen<CR>
	nnoremap <F5> :wa <CR> :!g++ % -o comp_%:r ;  ./comp_%:r <CR>
	nnoremap <F6> :wa <CR> :!g++ -lCGAL -lmpfr -lgmp -frounding-math % -o comp_%:r && ./comp_%:r <CR>
	nnoremap <F7> :wa <CR> :!g++ --std=c++11 -g -lCGAL -lmpfr -lgmp -frounding-math % -o comp_%:r -O2 && ./comp_%:r < input.txt<CR>
	nnoremap <F8> :wa <CR> :!g++ -g -lasan --std=c++11 -lCGAL -lmpfr -lgmp -frounding-math % -o comp_%:r -O2; ./comp_%:r < input.txt<CR>
	nnoremap <F10> :wa <CR> :!python ~/submitter.py %; <CR>

	"gdb debugging
	nnoremap <F11> :!g++ -g %<CR>:VBGstartGDB a.out<CR>:VBGrawWrite break main<CR>:VBGrawWrite run < input.txt<CR>:VBGtoggleTerminalBuffer<CR><C-w>H

	"nnoremap <F4> :wa <CR> :!./build.sh; cd bin; ./myview; <CR>

	"setlocal foldcolumn=1

	abbr vi vector<int>
	abbr vvi vector<vector<int> >
	abbr fori for(int i = 0; i < n; i++) {<CR>
	inoremap {<CR> {<CR><CR>}<ESC>kcc
endfunction

"			==== Java  ====
function! SettingsJava() 
	"autocmd FileType java setlocal omnifunc=javacomplete#Complete
	"autocmd filetype Java set completefunc=javacomplete#CompleteParamsInfo
	abbr print System.out.println();<Esc>hh
	"//	nnoremap <F4> :wa <CR> :!/c/progetti/android/LOL/build.sh; /c/progetti/android/LOL/run.sh <CR>
	"nnoremap <F5> :wa <CR> :!/c/progetti/android/RajawaliVRCardboard/build_and_run.sh <CR>

	nnoremap <F4> :wa <CR> :!javac %; java Main < input.txt <CR>
	nnoremap <F5> :wa <CR> :!javac %; java %:r <CR>
	"setlocal omnifunc=javacomplete#Complete
	"set makeprg=javac\ -d\ \~\/comp\ $(find\ gapp\ -name\ \"\*\.java\"\)
	set makeprg=javac\ -d\ \~\/comp\ $(find\ -name\ \"\*\.java\"\)
	"cp -r resources/* ~/comp/resources/; javac -d ~/comp (find gapp -name "*.java"); java -cp ~/comp gapp.ulg.test.slideNewTry
	set errorformat=%A%f:%l:\ %m,%-Z%p^,%-C%.%#
	map <F9>  <Esc>:silent :make<Return>:copen<Return><C-l>
	map <F10> <Esc>:cprevious<Return>
	map <F11> <Esc>:cnext<Return>
	map <F12> <Esc>:silent :!java -cp ~/comp gapp.ulg.test.slideNewTry<CR>

	set shiftwidth=4
	set softtabstop=4
	set tabstop=4
endfunction

"			==== Python ====
function! SettingsPython() 
	nnoremap <F3> :wa <CR> :!python2 % <CR>
	nnoremap <F4> :wa <CR> :!python % <CR>
	nnoremap <F5> :wa <CR> :!python spiketrap/lib/classifier_example.py <CR>
	setlocal foldcolumn=1
endfunction

"			==== Latex ====
function! SettingsLatex() 
	nnoremap <F4> :wa <CR> :!xelatex --shell-escape % <CR>
	nnoremap <F5> :wa <CR> :!zathura %:r.pdf <CR>
	setlocal nocursorline
    set foldmethod=manual
	set foldcolumn=0
	"set regexpengine=1
	"set colorcolumn=80
	"highlight ColorColumn ctermbg=DarkGrey guibg=lightgrey

	"syn sync minlines=10
	"syn sync maxlines=50

	:NoMatchParen
	setlocal updatetime=1000
	abbr ,a \begin{align*}<CR><CR>\end{align*}<UP>
	inoremap {<CR> {<CR><CR>}<ESC>kcc

	set conceallevel=2
	set concealcursor=vc
	let g:tex_conceal="adgms"
	highlight Conceal NONE
endfunction

"			==== HTML ====
function! SettingsHTML()  
map <F9> :!google-chrome-stable % 2> /dev/null > /dev/null &<CR><CR><C-L>
	" ROBA PER RADARE "
	set cindent
	set tabstop=2
	set noexpandtab
	set shiftwidth=2
	set softtabstop=2
	set cino=:0,+0,(2,J0,{1,}0,>8,)1,m2
    set foldmethod=manual
	set foldcolumn=0
endfunction "====
"			==== Bash ====
function! SettingsSh()
	map <F4> :!./%<CR>
endfunction


" ====

autocmd filetype c call SettingsC()
autocmd filetype h call SettingsC()
"autocmd filetype cpp call SettingsC()
autocmd filetype java call SettingsJava()
autocmd filetype python call SettingsPython()
autocmd filetype tex call SettingsLatex()
autocmd filetype html call SettingsHTML()
autocmd filetype sh call SettingsSh()

autocmd BufNewFile *.cpp r ~/.vim/usaco_template.cpp

" ===

"                  === other things ===

" ==== auto-install vim-plug ====
if empty(glob('~/.vim/autoload/plug.vim'))
  silent !curl -fLo ~/.vim/autoload/plug.vim --create-dirs 
    \ https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
  autocmd VimEnter * PlugInstall --sync | source $MYVIMRC 
endif " ====

" ==== custom folds ====
function! NeatFoldText()
	let line = ' '.substitute(getline(v:foldstart), '["#\/%!]*\s\+=\+\s*', '','g').' '
	let line = repeat('[', v:foldlevel) . line . repeat(']', v:foldlevel)
	let lines_count = v:foldend - v:foldstart + 1
	let lines_count_text = '| ' . printf("%10s", lines_count . ' lines') . ' |'
	let foldtextstart = strpart(repeat('  ',v:foldlevel) . line , 0, (winwidth(0)*2)/3)
	let foldtextend = lines_count_text . repeat(' ', 8)
	let foldtextlength = strlen(foldtextstart . foldtextend) + &foldcolumn
	return foldtextstart . repeat(' ', winwidth(0)-foldtextlength) . foldtextend
endfunction

set foldtext=NeatFoldText()
" ====

" ==== custom functions ====
let g:Toggled = 0
function! Hardtime_toggle()
	if g:Toggled == 1
		let g:Toggled = 0
		nnoremap j j
		nnoremap k k
		nnoremap l l
		nnoremap h h
	else
		let g:Toggled = 1
		nnoremap j <NOP>
		nnoremap k <NOP>
		nnoremap l <NOP>
		nnoremap h <NOP>
	endif
endfunction
" ====

" ==== custom status bar ====
"
" borrowed from @jvoisin
" https://dustri.org/b/lightweight-and-sexy-status-bar-in-vim.html
set statusline=
set statusline+=%#DiffAdd#%{(mode()=='n')?'\ \ NORMAL\ ':''}
set statusline+=%#DiffChange#%{(mode()=='i')?'\ \ INSERT\ ':''}
set statusline+=%#DiffDelete#%{(mode()=='r')?'\ \ RPLACE\ ':''}
set statusline+=%#Cursor#%{(mode()=='v')?'\ \ VISUAL\ ':''}
set statusline+=\ %n\           " buffer number
set statusline+=%#Visual#       " colour
set statusline+=%{&paste?'\ PASTE\ ':''}
set statusline+=%{&spell?'\ SPELL\ ':''}
set statusline+=%#CursorIM#     " colour
set statusline+=%R                        " readonly flag
set statusline+=%M                        " modified [+] flag
set statusline+=%#Cursor#               " colour
set statusline+=%#CursorLine#     " colour
set statusline+=\ %t\                   " short file name
set statusline+=%=                          " right align
set statusline+=%#CursorLine#   " colour
set statusline+=\ %Y\                   " file type
set statusline+=%#CursorIM#     " colour
set statusline+=\ %3l:%-2c\         " line + column
set statusline+=%#Cursor#       " colour
set statusline+=\ %3p%%\                " percentage
set laststatus=2
" ====

" ==== day/night dynamic colors ====
"
if strftime('%H') % 17 > 7
	set background=light
	colo solarized
else
	set background=dark
	colo alduin
endif
" ====



"--- ascii color values
"BLACK= "\u001b[30m"
"RED= "\u001b[31m"
"GREEN= "\u001b[32m"
"YELLOW= "\u001b[33m"
"BLUE= "\u001b[34m"
"MAGENTa= "\u001b[35m"
"CYAN= "\u001b[36m"
"WHITE= "\u001b[37m"
"RESET= "\u001b[0m"
"BOLD = '\033[1m'
"UNDERLINE = '\033[4m'

"loading = ["-", "\\", "|", "/"]




" regular expressions that match numbers (order matters .. keep '\d' last!)
" note: \+ will be appended to the end of each
let s:regNums = [ '0b[01]', '0x\x', '\d' ]

function! s:inNumber()
	" select the next number on the line
	" this can handle the following three formats (so long as s:regNums is
	" defined as it should be above this function):
	"   1. binary  (eg: "0b1010", "0b0000", etc)
	"   2. hex     (eg: "0xffff", "0x0000", "0x10af", etc)
	"   3. decimal (eg: "0", "0000", "10", "01", etc)
	" NOTE: if there is no number on the rest of the line starting at the
	"       current cursor position, then visual selection mode is ended (if
	"       called via an omap) or nothing is selected (if called via xmap)

	" need magic for this to work properly
	let l:magic = &magic
	set magic

	let l:lineNr = line('.')

	" create regex pattern matching any binary, hex, decimal number
	let l:pat = join(s:regNums, '\+\|') . '\+'

	" move cursor to end of number
	if (!search(l:pat, 'ce', l:lineNr))
		" if it fails, there was not match on the line, so return prematurely
		return
	endif

	" start visually selecting from end of number
	normal! v

	" move cursor to beginning of number
	call search(l:pat, 'cb', l:lineNr)

	" restore magic
	let &magic = l:magic
endfunction

" "in number" (next number after cursor on current line)
xnoremap <silent> in :<c-u>call <sid>inNumber()<cr>
onoremap <silent> in :<c-u>call <sid>inNumber()<cr>


"" vim:fdm=expr:fdl=0
"" vim:fde=getline(v\:lnum)=~'===*$'?(getline(v\:lnum)=~'==\\+[^=]\\+==.*'?'>'\:'<').(strlen(matchstr(getline(v\:lnum),'==*$'))-2)\:'='
