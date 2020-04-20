# demostream

Streaming service for great demoscene examples (c64, amiga, zx spectrum, etc)

### Try it live here -> [demostream.cyanpencil.xyz](http://demostream.cyanpencil.xyz)


# Instructions
- Put your demos in the `video/<category>/` folder. Accepted formats are `mp4`, `webm` and `mkv` (e.g. if you have a c64 demo, put it in `video/c64/example.mp4`). Your demos will be played at random to the nginx server which will serve them through `hls` (http live streaming) on port 8080.

- (optional) change `demostream.m3u8` to point to the url where you will be serving the stream (only necessary to play the stream through custom players such as mpv)

- Change `run.sh` if you use `docker` instead of `podman`

- run `docker build -t demostream .`

- then execute `./run.sh`


# Credits

[Oldschool PC fonts](https://int10h.org/oldschool-pc-fonts/fontlist/) - for the IBM VGA fonts used in the frontend

[ffmpeg](https://www.ffmpeg.org) - complex but wonderful tool 

[pouet.net](http://www.pouet.net) - where I got the demos from

Sebastian Ramirez - for the initial version of the Dockerfile
