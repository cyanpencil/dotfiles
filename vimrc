set nocompatible
set ai
set backspace=indent,eol,start
set history=50
"set ruler
set background=dark
set showcmd
set incsearch
set hlsearch
let python_highlight_all=1
syntax on
set number
set cindent
set smartindent
set ignorecase
set smartcase
set tabstop     =4
set softtabstop =4
set shiftwidth  =4
set scrolloff   =4
set autowrite

set showbreak=>

"set ttimeoutlen =10

set noequalalways
"hi NonText ctermfg=black guifg=black

set foldenable
set foldmethod=syntax
set foldlevel=99


call plug#begin('~/.vim/bundle')
"Plug 'tmhedberg/SimpylFold'
"Plug 'vim-scripts/indentpython.vim'
"Plug 'hdima/python-syntax'
Plug 'godlygeek/csapprox'
Plug 'flazz/vim-colorschemes'
Plug 'jnurmine/Zenburn'
Plug 'altercation/vim-colors-solarized'
Plug 'scrooloose/nerdtree'
Plug 'majutsushi/tagbar'
Plug 'ervandew/supertab'
Plug 'airblade/vim-rooter'
Plug 'takac/vim-hardtime'
Plug 'ctrlpvim/ctrlp.vim'
Plug 'godlygeek/tabular'
Plug 'rhysd/clever-f.vim'
"Plug 'vim-scripts/Gundo'
Plug 'scrooloose/nerdcommenter'
Plug 'EinfachToll/DidYouMean'
Plug 'Konfekt/FastFold'
Plug 'mileszs/ack.vim'
"Plug 'scrooloose/syntastic'
"Plug 'artur-shaik/vim-javacomplete2'
Plug 'Shougo/neocomplete.vim'
"Plug 'mbbill/undotree'
Plug 'chrisbra/histwin.vim'
Plug 'matze/vim-move'

Plug 'junegunn/fzf', { 'dir': '~/.fzf', 'do': './install --all' }
Plug 'junegunn/fzf.vim'
Plug 'metakirby5/codi.vim'

Plug 'vim-airline/vim-airline'
Plug 'vim-airline/vim-airline-themes'
"Plug 'itchyny/lightline.vim'
call plug#end()

colorscheme alduin
set expandtab
set autoindent
set fileformat=unix
set encoding=utf-8
nnoremap <space> za


"autocmd FileType java setlocal omnifunc=javacomplete#Complete


set backupdir=~/.vim_backup,.
set directory=~/.vim_backup,.


"inoremap jj <ESC>
"nnoremap J o<ESC>k
"nnoremap K O<ESC>j

nnoremap h <C-w>h
nnoremap j <C-w>j
nnoremap k <C-w>k
nnoremap l <C-w>l

"noremap p ]p
"nnoremap P [p


"inoremap <CR> <CR>x<BS>
"nnoremap o ox<BS>
"nnoremap O Ox<BS>



nnoremap ,e :s/^/\/\/<CR>:nohl<CR>
vnoremap ,e :s/^/\/\/<CR>:nohl<CR>
nnoremap ,E :s/\/\///<CR>:nohl<CR>
vnoremap ,E :s/\/\///<CR>:nohl<CR>

set mouse=a
map <ScrollWheelUp> <C-Y><C-Y>
map <ScrollWheelDown> <C-E><C-E>

inoremap {<CR> {<CR><CR>}<ESC>kcc

autocmd filetype cpp call SettingsCpp()
autocmd filetype java call SettingsJava()
autocmd filetype python call SettingsPython()

autocmd BufNewFile *.cpp r ~/.vim/usaco_template.cpp

autocmd BufReadPre * HardTimeOn

function! SettingsCpp()
    nnoremap <F5> :wa <CR> :!g++ % -o comp_%:r ;  ./comp_%:r <CR>
    nnoremap <F6> :wa <CR> :!make;  cygstart ./run <CR>
    nnoremap ,e :s/^/\/\//<CR>:nohl<CR>
    vnoremap ,e :s/^/\/\//<CR>:nohl<CR>
    nnoremap ,E :s/\/\///<CR>:nohl<CR>
    vnoremap ,E :s/\/\///<CR>:nohl<CR>

    set foldcolumn=2
endfunction

function! SettingsJava()
    "//	nnoremap <F4> :wa <CR> :!/c/progetti/android/LOL/build.sh; /c/progetti/android/LOL/run.sh <CR>
	"nnoremap <F5> :wa <CR> :!/c/progetti/android/RajawaliVRCardboard/build_and_run.sh <CR>

    "nnoremap <F4> :wa <CR> :!~/java_compile.sh <CR>
    "setlocal omnifunc=javacomplete#Complete
    "set makeprg=javac\ -d\ \~\/comp\ $(find\ gapp\ -name\ \"\*\.java\"\)
    set makeprg=javac\ -d\ \~\/comp\ $(find\ -name\ \"\*\.java\"\)
"cp -r resources/* ~/comp/resources/; javac -d ~/comp (find gapp -name "*.java"); java -cp ~/comp gapp.ulg.test.slideNewTry
    set errorformat=%A%f:%l:\ %m,%-Z%p^,%-C%.%#
    map <F9>  <Esc>:silent :make<Return>:copen<Return><C-l>
    map <F10> <Esc>:cprevious<Return>
    map <F11> <Esc>:cnext<Return>
    map <F12> <Esc>:silent :!java -cp ~/comp gapp.ulg.test.slideNewTry<CR>
endfunction

function! SettingsPython()
	nnoremap <F4> :wa <CR> :!python % <CR>
	nnoremap <F5> :wa <CR> :!python % <CR>
	nnoremap ,e :s/^/#/<CR>:nohl<CR>
	vnoremap ,e :s/^/#/<CR>:nohl<CR>
	nnoremap ,E :s/#//<CR>:nohl<CR>
	vnoremap ,E :s/#//<CR>:nohl<CR>
endfunction

":map <C-U> 2<C-Y>2<C-Y>2<C-Y>2<C-Y>2<C-Y>2<C-Y>2<C-Y>2<C-Y>
":map <C-D> 2<C-E>2<C-E>2<C-E>2<C-E>2<C-E>2<C-E>2<C-E>2<C-E>



:map [[ ?{<CR>w99[{
:map ][ /}<CR>b99]}
:map ]] j0[[%/{<CR>
:map [] k$][%?}<CR>

nnoremap gV `[v`]

"salva i folds!!
"au BufWrite ?* mkview
"au BufReadPre ?* silent loadview


abbr vi vector<int>
abbr vvi vector<vector<int> >
abbr fori for(int i = 0; i < n; i++) {<CR>



function! SmoothScroll(up)
	if a:up
		let scrollaction=""
	else
		let scrollaction=""
	endif
	exec "normal " . scrollaction
	redraw
	let counter=1
	while counter<&scroll
		let counter+=1
		sleep 10m
		redraw
		exec "normal " . scrollaction
	endwhile
endfunction

nnoremap <C-U> :call SmoothScroll(1)<Enter>
nnoremap <C-D> :call SmoothScroll(0)<Enter>
inoremap <C-U> <Esc>:call SmoothScroll(1)<Enter>i
inoremap <C-D> <Esc>:call SmoothScroll(0)<Enter>i

nnoremap ,n :NERDTreeToggle<Enter>
nnoremap ,t :TagbarToggle<Enter>

"move lines up and down"
"nnoremap <C-J> :m .+1<CR>==
"nnoremap <C-K> :m .-2<CR>==
"vnoremap <C-J> :m '>+1<CR>gv=gv
"vnoremap <C-K> :m '<-2<CR>gv=gv
"inoremap <C-J> <Esc>:m .+1<CR>==gi
"inoremap <C-K> <Esc>:m .-2<CR>==gi

"change tabs
nnoremap H gT
nnoremap L gt


"auto reload vim_rc
augroup reload_vimrc " {
    autocmd!
    autocmd BufWritePost $MYVIMRC source $MYVIMRC
augroup END " }

nnoremap ,T  : Tabularize /=<CR>
vnoremap ,T  : Tabularize /=<CR>
nnoremap ,,T : Tabularize /=/r1c1r0<CR>
vnoremap ,,T : Tabularize /=/r1c1r0<CR>

nnoremap q/ q/i
nnoremap ,g :GundoToggle<CR>

nnoremap ,cc <Leader>cc
nnoremap ,c<Space> \cm

nnoremap <c-l> :nohl<cr><c-l>
nnoremap ,p "+]p
nnoremap ,P "+]P

nmap ,cc <Leader>c<Space>
nmap ,cm <Leader>cm
nmap ,cs <Leader>cs
vmap ,cc <Leader>c<Space>
vmap ,cm <Leader>cm
vmap ,cs <Leader>cs
vmap ,C <Leader>c<Space>
"vmap ;C <Leader>c<Space>
":call Hardmode()
"nnoremap :w!! :w !sudo tee %

set listchars=eol:⏎,tab:>-,trail:.,nbsp:.

nnoremap ,W :%s/\s\+$//<cr>:let @/=''<CR>

set completeopt=menuone,longest,preview
"autocmd filetype Java set completefunc=javacomplete#CompleteParamsInfo

let g:JavaComplete_SourcesPath = "~/progetti/silvestri/java-project/gapp"
"let g:syntastic_java_checkstyle_classpath = "~/Download/checkstyle.jar"
let g:syntastic_mode_map = {
    \ "mode": "passive",
    \ "active_filetypes": [],
    \ "passive_filetypes": [] }

nmap p ]pgV=
nmap P [pgV=

set cursorline

nmap - /
nmap _ ?
vmap - /
vmap _ ?

set cscopequickfix=s-,c-,d-,i-,t-,e-,a-

nnoremap <C-\> <C-w>}
let g:airline_theme = "zenburn"
let g:move_key_modifier = 'C'

abbr print System.out.println();<Esc>hh
"abbr todo // TODO <Esc>:r! date +\%Y/\%m/\%d\ \%H:\%M\ <CR>

iab <expr> dts strftime("%c")
iab <expr> todo "// TODO ".strftime('%Y/%m/%d %H:%M')." - "

nnoremap ,v :vs $MYVIMRC<CR>
nnoremap ,u yiw:cs find s <C-R>0<CR>:copen<CR>


let g:ctrlp_cmd = 'CtrlPMRUFiles'

nnoremap ,w :AckWindow!<Space>
nnoremap ,f :Ack!<Space>

let g:ackprg = 'ag --vimgrep --smart-case'
let g:ack_use_cword_for_empty_search = 1

nnoremap ,x :ccl<CR>

let g:fzf_action = {
            \ 'ctrl-t': 'tab split',
            \ 'ctrl-x': 'split',
            \ 'ctrl-v': 'vsplit' }

nnoremap Q :q!<CR>
nnoremap <Esc> :wq<CR>
nnoremap U <C-R>
nnoremap ,s :w<CR>
nnoremap ,q :wq<CR>
nnoremap ,Q :q!<CR>

inoremap jk <Esc>
inoremap kj <Esc>
inoremap <Esc> <nop>
inoremap ò <Esc>

nnoremap H g^
nnoremap L g$

nnoremap K {
nnoremap J }


