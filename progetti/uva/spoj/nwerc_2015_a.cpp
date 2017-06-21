#include <iostream>
#include <algorithm>
#include <fstream>
#include <queue>
using namespace std;

int n, m, total_pc = 0, sol = 0;
vector<pair<int, int> > v;
priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair< int, int> > > free_pc;
int time_free[300000];

int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        v.push_back(pair<int, int>(x, x + y));
    }
    sort(v.begin(), v.end());
    //for (int i = 0; i < n; i++) printf("(%d, %d)\n", v[i].first, v[i].second);
    priority_queue<pair<int, int>, vector<pair <int, int> >, greater< pair<int, int> > > q;


    for (int i = 0; i < n; i++) {
        int t = v[i].first;
        // libero i computer dei ricercatori che se  ne sono andati
        while (! q.empty()) {
            pair<int , int> p = q.top();
            if (t >= p.first) q.pop();
            else break;
            free_pc.push(pair<int, int>(p.first, p.second));
            //free_pc.
            //time_free[p.second] = p.first;
        }
        
        while (!free_pc.empty()) {
            pair<int, int> pc = free_pc.top();
            if (t - pc.first <= m) 
                break;
            free_pc.pop();
        }

        //if (!free_pc.empty()) printf("free pcfront : (%d, %d)\n", free_pc.top().first, free_pc.top().second);
        
        if (!free_pc.empty()) {
            pair<int, int> pc = free_pc.top();
            free_pc.pop();
            q.push(pair<int, int>(v[i].second, pc.second));
            //cout<<"rimetto in coda a tempo  "<<v[i].first<<  "  il pc " << pc.second<<endl;
        }
        else {
            total_pc++;
            sol++;
            //cout<<"mi serve un nuovo pc"<<total_pc<<"al tempo "<<v[i].first<<endl;
            q.push(pair<int,int>(v[i].second, total_pc));
        }



















        //cerco un pc libero come ?
        //
        //pair<int, int> pc(-1, -1);
        //if (!free_pc.empty()) {
            //pc = free_pc.top();
            //free_pc.pop();
        //}
        //cout << i  << endl;
        
        //while (!free_pc.empty() && t - pc.first >= m) {
            ////cout << i  << endl;
            ////sol++;
            //pc = free_pc.top();
            //free_pc.pop();
        //}
        //printf("Ho preso il pc (%d, %d)\n", pc.first, pc.second);
        //if (pc.first != -1 && t - pc.first < m) {
            //q.push(pc);
        //}
        //else {
            //total_pc ++;
            //cout << i << endl;
            //sol++;
            //pc = pair<int, int>(t, total_pc);
            //q.push(pc);
        //}



        //if (free_pc.empty() && t - pc.first >= m) {
            //total_pc ++;
            //sol++;
            //cout << i  << endl;
            //pc = pair<int, int>(t, total_pc);
            //q.push(pc);
        //}
        //else if (! free_pc.empty()) {
            //free_pc.pop();
            //q.push(pc);
        //}
        //}
        //q.push(pair<int, int>(v[i].second, pc));
    }
    cout <<n-sol << endl;
} 
