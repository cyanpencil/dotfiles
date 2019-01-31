#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
#include <set>
using namespace std;

struct wire{
    wire* in1;
    wire* in2;
    char output = '?';
    bool element = false;
    bool visited = false;
    set<int, std::less<int>, std::allocator<int> > ports;  
    int i;
};

wire inputs[20000];
wire elements[20000];

void get_son(wire* e) {
    if(e->in1->element) get_son(e->in1);
    if(e->in2->element)  get_son(e->in2);
    e->ports.insert(e->in1->ports.begin(), e->in1->ports.end());
    e->ports.insert(e->in2->ports.begin(), e->in2->ports.end());
}

char visit(wire* e) {
    //cout << "Sono arrivato a " << e->i << endl;
    if (e->element == false) return e->output;
    if (e->visited == true) return e->output;
    e->visited = true;
    if (e->output == '?') {
        //cout << "Scendo in " <<  e->in1->i << endl;
        char uno = visit(e->in1);
        //cout << "Finito di scendere in " << e->in1->i << endl;
        //cout << "Scendo in " <<  e->in2->i << endl;
        char due = visit(e->in2);
        //cout << "Finito di scendere in " << e->in2->i << endl;
        if (uno == '1' || due == '1') {
            e->output = '1';
            return '1';
        }
        else if (uno == '0' && due == '0') {
            e->output = '0';
            return '0';
        }
        else {
            e->output = '?';
            return '?';
        }
    }
    else {
        if (e->output == '0') {
            e->in1->output = '0';
            e->in2->output = '0';
            visit(e->in1);
            visit(e->in2);
        }
        else if (e->output == '1') {
            if (e->in1->output == '0') e->in2->output = '1';
            if (e->in2->output == '0') e->in1->output = '1';
            visit(e->in1);
            visit(e->in2);
        }
        return e->output;
    }
}


int main() {
    //freopen("input.txt", "r", stdin);
    int n, m;
    scanf("%d %d", &n, &m);
    for (int i = 0; i < m; i++) {
        elements[i].element = true;
        char k;
        scanf(" %c", &k);
        elements[i].output = k;
        elements[i].i = i;
    }
    for (int i = 0; i < n; i++) {
        inputs[i].i = -i;
        inputs[i].ports.insert(i);
        
    }
    for (int i = 0; i < m; i++) {
        int s;
        char t;
        scanf(" %c", &t);
        scanf(" %d", &s);
        if (t == 'x') {
            elements[i].in1 = &inputs[s - 1];
        }
        else {
            elements[i].in1 = &elements[s - 1];
        }
        scanf(" %c", &t);
        scanf(" %d", &s);
        if (t == 'x') {
            elements[i].in2 = &inputs[s - 1];
        }
        else {
            elements[i].in2 = &elements[s - 1];
        }
    }

    for (int i = 0; i < m; i++) {
        if (elements[i].ports.size() == 0) get_son(& elements[i]);
    }


    for (int k = 0; k <= m/2; k++) {
        for (int i = 0; i < m; i++) elements[i].visited = false;
        for (int i = m - 1; i >= 0; i --) {
            if (!elements[i].visited) {
                //cout << "Adesso visito " << i << endl;
                visit(& elements[i]);
                //cout << "Finito di visitare " << i << endl;
            }
        }

    }

    for (int i = 0; i < m; i++) {
        if (elements[i].output == '?') {
            for (int j = 0; j < m; j++) {
                if (j != i) {
                    if (elements[j].output == '1') {
                        if (includes(elements[i].ports.begin(), elements[i].ports.end(), elements[j].ports.begin(), elements[j].ports.end())) {
                            elements[i].output = '1';
                        }
                    }
                }
            }
        }
    }

    //cout << endl;
    for (int i = 0; i < m; i++)
        cout << elements[i].output;
    //cout << endl;
    //for (set<int>::const_iterator p = elements[4].ports.begin( );p != elements[4].ports.end( ); ++p)
        //cout << *p;
    //for (set<int>::const_iterator p = inputs[0].ports.begin( );p != inputs[0].ports.end( ); ++p)
        //cout << *p;
}
