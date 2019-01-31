#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
using namespace std;

struct rect {
    int x, y, w, h;
    rect(int _x, int _y, int _w, int _h) {
        x = _x; y = _y; w = _w; h = _h;
    }
};


int n;
vector<rect> rects;
vector<int> ord;
vector<vector<int> > e;
long long sol = 0;
bool visited[60000] ;

long long bfs(int s) {
    long long c = 0;
    queue<int> q;
    q.push(s);
    while(q.size() > 0) {
        int a = q.front(); q.pop();
        c += rects[a].w * rects[a].h;
        for (int i = 0; i < e[s].size(); i++) {
            if (!visited[e[s][i]]) q.push(e[s][i]);
        }
    }
    return c;
}

bool compare_xx(rect a, rect b) {
    if (a.x < b.x) return true;
    if (a.x == b.x) return a.y < b.y;
    return false;
}

bool compare_yy(rect a, rect b) {
    if (a.y < b.y) return true;
    if (a.y == b.y) return a.x < b.x;
    return false;
}

bool compare_x(int A, int B) {
    rect a = rects[A], b = rects[B];
    return compare_xx(a, b);
}
bool compare_y(int A, int B) {
    rect a = rects[A], b = rects[B];
    return compare_yy(a, b);
}

int binary_search(rect A, bool (*f)(rect, rect)) {
    //rect a = rects[ord[A]];
    int lo = 0, hi = rects.size() - 1;
    while (lo < hi) {
        int m = (lo + hi) / 2;
        if ((*f)(rects[ord[m]], A)) lo = m + 1;
        else              hi = m - 1;
    }
    return (*f)(rects[ord[lo]], A) ? lo + 1: lo;
}

void addedge(int i, int j) {
    printf("Collego %d con %d \n", i, j);
    e[i].push_back(j);
    e[j].push_back(i);
}



int main() {
    memset(visited, 0, sizeof visited);
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y, w, h;
        cin >> x >> y >> w >> h;
        rects.push_back(rect(x, y, w, h));
        ord.push_back(i);
        e.push_back(vector<int>());
    }

    sort(ord.begin(), ord.end(), compare_x);
    for (int i = 0; i < n; i++) { //forse deve essere n - 1
        int j = binary_search(rect(rects[ord[i]].x, rects[ord[i]].y, 0, 0), compare_xx);
        if (rects[ord[j]].y <= rects[ord[i]].y + rects[ord[i]].h && rects[ord[i]].y >= rects[ord[j]].y + rects[ord[j]].h) {
            addedge(ord[i], ord[j]);
        }
        //printf("%d %d\n", rects[ord[i]].x, rects[ord[i]].y);
    }

    sort(ord.begin(), ord.end(), compare_y);
    for (int i = 0; i < n; i++) {
        int j = binary_search(rect(rects[ord[i]].x, rects[ord[i]].y, 0, 0), compare_yy);
        if (rects[ord[j]].x <= rects[ord[i]].x + rects[ord[i]].w && rects[ord[i]].x >= rects[ord[j]].x + rects[ord[j]].w) {
            addedge(ord[i], ord[j]);
        }
    }
    //printf("%d", binary_search(rect(13, 1, 0, 0), compare_xx));
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            sol = max(sol, bfs(i));
        }
    }
    cout << sol << endl;

}
