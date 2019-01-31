// #include<bits/stdc++.h>
#include "stdc++.h"

using namespace std;

static unsigned int g_seed;

// Used to seed the generator.
inline void fast_srand(int seed) {
    g_seed = seed;
}

// Compute a pseudorandom integer.
// Output value in range [0, 32767]
inline int fast_rand(void) {
    g_seed = (214013*g_seed+2531011);
    return (g_seed>>16)&0x7FFF;
}

// Test cases
//     R     C   F     N    B      T
//     3     4   2     3    2     10
//   800  1000 100   300   25  25000
//  3000  2000  81 10000    1 200000
// 10000 10000 400 10000    2  50000
//  1500  2000 350 10000 1000 150000

struct ride {
  // s_row = the row of the start intersection (0 ≤ a < R)
  // s_col = the column of the start intersection (0 ≤ b < C)
  // f_row = the row of the finish intersection (0 ≤ x < R)
  // f_col = the column of the finish intersection (0 ≤ y < C)
  int s_row, s_col, f_row, f_col;

  // start = the earliest start(0 ≤ s < T)
  // finish = the latest finish - ridelen
  int start, finish;

  // we have completed the ride
  bool done;

  // if of the request
  int id;
};

ride rides[10001];
vector<int> ans[401];
int curRow[401], curCol[401], turns[401];

int R, C, F, N, B, T;

// Distance of the ride
inline int ridelen(ride r) {
  // |x − a| + |y − b|
  return abs(r.f_row - r.s_row) + abs(r.f_col - r.s_col);
}

// Distance from the start of a ride to an intersection
inline int dist(ride r, int row, int col)
{
  return abs(row - r.s_row) + abs(col - r.s_col);
}

bool cmp(ride A, ride B) {
  return A.start < B.start;
}

int main() {
  // freopen("input.in","r",stdin);
  // freopen("output.out","w",stdout);
  ios_base::sync_with_stdio(false); // faster I/O
  cin.tie(0); // :P

  fast_srand (time(NULL));

  // INPUT - BEGIN
  // R = number of rows of the grid (1 ≤ R ≤ 10000)
  // C = number of columns of the grid (1 ≤ C ≤ 10000)
  // F = number of vehicles in the fleet (1 ≤ F ≤ 1000)
  // N = number of rides (1 ≤ N ≤ 10000)
  // B = per-ride bonus for starting the ride on time (1 ≤ B ≤ 10000)
  // T = number of steps in the simulation (1 ≤ T ≤ 10^9)
  cin >> R >> C >> F >> N >> B >> T;
  for (int i = 0; i < N; i++) {
    ride &r = rides[i];
    cin >> r.s_row >> r.s_col >> r.f_row >> r.f_col >> r.start >> r.finish;
    r.finish -= ridelen(r);
    r.done = false;
    r.id = i;
  }
  // INPUT - END



  // sort(rides, rides + N, cmp);

  while (true)
  {
    int nxt = -1;
    long double mn = 1e9 + 1;
    int nxtCar = -1;
    int begTime = -1;
    for (int i = 0; i < N; i++)
    {
      ride &r = rides[i];
      if(r.done == true)
        continue;
      for(int curCar = 0;curCar < F;curCar++)
      {
        int reachTime = max(r.start, turns[curCar] + dist(r, curRow[curCar], curCol[curCar]));
        int bonus = 0;
        if(reachTime == r.start)
          bonus = B;
        float random = (fast_rand() % 100) / 3000.0;
        long double weight = (1.0 - random) * (reachTime - bonus);
        //long double weight = reachTime - bonus + (r.finish - r.start); I am trying it with this one // i think is better
        //long double weight = reachTime - bonus + (r.finish - r.start)/100.0;//but this one might require mn = 1e18
        if (reachTime < r.finish && weight < mn)
        {
          nxt = i;
          //mn = reachTime ;
          mn = weight;
          nxtCar = curCar;
          begTime = reachTime;
        }
      }
    }
    if (nxt == -1) break;

    ride &nxtRide = rides[nxt];
    nxtRide.done = true;
    ans[nxtCar].push_back(nxtRide.id);
    turns[nxtCar] = begTime + ridelen(nxtRide);
    curRow[nxtCar] = nxtRide.f_row;
    curCol[nxtCar] = nxtRide.f_col;
  }




  // OUTPUT - BEGIN
  for (int curCar = 0; curCar < F; curCar++) {
    cout << ans[curCar].size();
    for(int i=0; i<ans[curCar].size(); i++) {
      cout << " " << ans[curCar][i];
    }
    cout << "\n";
  }
  // OUTPUT - END

  return 0;
}

