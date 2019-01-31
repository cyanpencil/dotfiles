#include <cstring>
#include <cstdio>
#include <cstdlib>
#include <cctype>
#include <cmath>
#include <string>
#include <vector>
#include <list>
#include <map>
#include <queue>
#include <stack>
#include <set>
#include <algorithm>
#include <sstream>
#include <iostream>
#include <fstream>
#define CLR(x) memset(x,0,sizeof(x))
#define OFF(x) memset(x,-1,sizeof(x))
#define MEM(x,a) memset((x),(a),sizeof(x))
#define ALL(x) x.begin(),x.end()
#define AT(i,v) for (auto &i:v)
#define For_UVa if (kase!=1) cout << endl
#define BUG cout << "I am here" << endl
#define lookln(x) cout << #x << "=" << x << endl
#define look(x) cout << #x << "=" << x
#define SI(a) scanf("%d",&a)
#define SII(a,b) scanf("%d%d",&a,&b)
#define SIII(a,b,c) scanf("%d%d%d",&a,&b,&c)
#define Lson l,mid,rt<<1
#define Rson mid+1,r,rt<<1|1
#define Root 1,n,1
#define BigInteger bign
template <typename T> T max(T& a,T& b) {return a>b?a:b;}
template <typename T> T min(T& a,T& b) {return a<b?a:b;}
int gcd(int a,int b) {return b==0?a:gcd(b,a%b);}
long long gcd (long long a,long long b) {return b==0LL?a:gcd(b,a%b);}
const int MAX_L=2005;// For BigInteger
const int INF_INT=0x3f3f3f3f;
const long long INF_LL=0x7fffffff;
const int MOD=1e9+7;
const double eps=1e-9;
const double pi=acos(-1);
typedef long long  ll;
using namespace std;

const int N=3000;

int n,m;
int pnt[N],from[N],nxt[N],head[N],cost[N];
int cnt;
void add_edge(int u,int v,int w) {
    from[cnt]=u;pnt[cnt]=v;nxt[cnt]=head[u];head[u]=cnt;
    cost[cnt++]=w;
}

int vis[N],dis[N],counter[N];
vector<int> ans;
int flag;
int in[N];
void bfs(int u) {
    queue<int> q;
    q.push(u);
    in[u]=1;
    ans.push_back(u);
    while (q.size()) {
        int x=q.front();q.pop();
        for (int i=head[x];~i;i=nxt[i]) {
            int v=pnt[i];
            if (in[v]) continue;
            ans.push_back(v);
            in[v]=1;
            q.push(v);
        }
    }
}

void spfa() {
    CLR(vis);CLR(in);
    for (int i=0;i<n;i++) dis[i]=INF_INT;
    CLR(counter);
    queue<int> q;
    for (int i=0;i<n;i++) {
        counter[i]=1;
        vis[i]=1;
        dis[i]=0;
        q.push(i);
    }
    while (q.size()) {
        int x=q.front();q.pop();
        vis[x]=0;
        for (int i=head[x];~i;i=nxt[i]) {
            int v=pnt[i];
            if (in[v]) continue;
            if (dis[v]>dis[x]+cost[i]) {
                dis[v]=dis[x]+cost[i];
                if (!vis[v]) {
                    q.push(v);
                    vis[v]=1;
                    counter[v]++;
                    if (counter[v]>n) {
                        bfs(v);flag=1;
                    }
                } 
            }
        }
    }
}

int main(){
#ifdef LOCAL
    freopen("C:\\Users\\john\\Desktop\\in.txt","r",stdin);
//  freopen("C:\\Users\\john\\Desktop\\out.txt","w",stdout);
#endif
    int T_T;
    for (int kase=scanf("%d",&T_T);kase<=T_T;kase++) {
        SII(n,m);
        OFF(head);cnt=0;
        for (int i=1;i<=m;i++) {
            int u,v,w;
            SIII(u,v,w);
            add_edge(v,u,w);
        }
        flag=0;
        ans.clear();
        spfa();
        cout << "Case " << kase << ": ";
        if (!flag) cout << "impossible\n";
        else {
            sort(ans.begin(),ans.end());
            ans.resize(unique(ans.begin(),ans.end())-ans.begin());
            for (int i=0;i<ans.size();i++) {
                if (i) cout << ' ';
                cout << ans[i];
            }
            cout << endl;
        }
    }
    return 0;
}
