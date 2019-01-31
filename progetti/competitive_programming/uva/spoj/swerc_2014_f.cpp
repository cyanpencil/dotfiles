#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

struct rect {
    int x, y, w, h, s = -1, original = -1;
    rect(int _x, int _y, int _w, int _h) {
        x = _x; y = _y; w = _w; h = _h;
    }
    rect(int _x, int _y, int _w, int _h, int o) {
        x = _x; y = _y; w = _w; h = _h; original = o;
    }
};

int n;
vector<set<int> > sets;
vector<rect> rects;
vector<rect> orig;

bool compare_x(const rect &a, const rect &b) {
    if (a.x < b.x) return true;
    if (a.x == b.x) return a.y < b.y;
    return false;
}

bool compare_y(const rect &a, const rect &b) {
    if (a.y < b.y) return true;
    if (a.y == b.y) return a.x < b.x;
    return false;
}

int binary_search(rect k, bool (*compare)(const rect &a, const rect &b)) {
    int lo = 0, hi = rects.size() - 1;
    while (lo < hi) {
        int m = (lo + hi) / 2;
        if ((*compare)(rects[m], k)) lo = m + 1;
        else                        hi = m - 1;
    }
    return (*compare)(rects[lo], k) ? lo + 1 : lo;
}

void aggiornaset(rect b, rect a) {
    if (b.s == -1 && a.s == -1) {
        set<int> temp;
        temp.insert(b.original);
        temp.insert(a.original);
        sets.push_back(temp);
        b.s = sets.size() - 1;
        a.s = b.s;
    }
    else if (b.s != -1 && a.s != -1) {
        printf("Mi sono capitati due pieni!\n");
        sets[b.s].insert(sets[a.s].begin(), sets[a.s].end());
        for (set<int>::iterator it = sets[a.s].begin(); it != sets[a.s].end(); it++) {
            rects[*it].s = b.s;
            orig[rects[*it].original].s = b.s;
        }
    }
    else if (b.s != -1) {
        a.s = b.s;
        orig[a.original].s = b.s;
        sets[a.s].insert(b.original);
    }
    else if (a.s != -1) {
        b.s = a.s;
        orig[b.original].s = a.s;
        sets[b.s].insert(a.original);
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y, w, h;
        cin >> x >> y >> w >> h;
        rects.push_back(rect(x, y, w, h, i));
        orig.push_back(rect(x, y, w, h, i));
    }
    sort(rects.begin(), rects.end(), compare_x);
    for (int i = 0; i < rects.size() - 1; i++) {
        int j = binary_search(rect(rects[i].x + rects[i].w, rects[i].y, 0, 0), compare_x);
        if (rects[j].x == rects[i].x + rects[i].w) {
            if (rects[j].y >= rects[i].y && rects[i].y <= rects[i].y + rects[i].h) {
                aggiornaset(rects[i], rects[j]);
                printf("Unisco %d(%d) con %d(%d)\n", i, rects[i].s, j, rects[j].s);
            }
        }
    }

    sort(rects.begin(), rects.end(), compare_y);
    for (int i = 0; i < rects.size() - 1; i++) {
        int j = binary_search(rect(rects[i].x, rects[i].y + rects[i].h, 0, 0), compare_y);
        if (rects[j].y == rects[i].y + rects[i].h) {
            if (rects[j].x >= rects[i].x && rects[i].x <= rects[i].x + rects[i].w) {
                aggiornaset(rects[i], rects[j]);
                printf("Unisco %d(%d) con %d(%d)\n", i, rects[i].s, j, rects[j].s);
            }
        }
    }

    long long sol = 0;
    for (int i = 0; i < sets.size(); i++) {
        long long m = 0;
        for (set<int>::iterator it = sets[i].begin(); it != sets[i].end(); it++) {
            rect r = orig[*it];
            m += r.w * r.h;
        }
        sol = max(sol, m);
    }
    cout << sol << endl;
}
