#include <iostream>
#include <fstream>
#include <vector>



using namespace std;

int rows, columns, L, H; //L is the minimum amount of each ingredient, H the max number of cells per slice
char M[1001][1001]; //M is the pizza matrix.
int cntM[1001][1001];
bool vis[1001][1001];

struct  Slice
{
    int r1,c1,r2,c2;

    void print()
    {
        cout<<r1-1<<" "<<c1-1<<" "<<r2-1<<" "<<c2-1<<"\n";
    }
};


void bigger(Slice s){
  int i,j;

  // Adding more column to the right
  for(j = 1;j+s.c2<=columns;j++)//j is equivalent to number of added columns
  {
    if((s.r2 - s.r1 + 1) * (s.c2 + j - s.c1 + 1) > H)
      break;
    bool can = true;
    for(i = s.r1;i <= s.r2; i++){
      if(vis[i][s.c2+j])
      {
        can = false;
        break;
      }
    }
    if(!can)
      break;
    for(i = s.r1;i <= s.r2; i++)
      vis[i][s.c2+j] = true;
  }
  s.c2 += j - 1;


}

/*
void setVis(int r1, int c1,int r2, int c2){
  for(int i = r1; i < r2; i++)
    for(int j = c1; j < c2; j++)
      vis[i][j] = 1;
}
*/

int main()
{
    ios_base::sync_with_stdio(false); // faster I/O

    // Reading input
    cin >> rows >> columns >> L >> H;
    for (int i = 1; i <= rows; i++)
    {
        for (int j = 1; j <=  columns; j++)
        {
            cin >> M[i][j];
        }
    }

    // Precalculating how many tomatoes in a slice from (1,1) to (i j)
    for(int i = 1; i <= rows; i++)
    {
        for(int j = 1; j <= columns; j++)
        {
            cntM[i][j] = (M[i][j]=='M') + cntM[i-1][j] + cntM[i][j-1] - cntM[i-1][j-1];
        }
    }

    vector<Slice> answer;
    for(int i = 1; i <= rows; i++) {
        for(int j = 1; j <= columns; j++) {
            if(!vis[i][j]) {
                int h,w;
                //bool can = false;
                int maxH = rows - i  + 1;
                int maxW = columns - j + 1;
                int ansH = -1, ansW=-1;
                for(h = 1;h<=maxH && h <= H; h++) {
                    for(w = 1; w<=maxW && h*w <=H; w++) {
                      if(vis[i+h-1][j+w-1]) {
                        maxW = w-1;
                        break;
                      }
                        int num_M = cntM[i+h-1][j+w-1] - cntM[i-1][j+w-1] - cntM[i+h-1][j-1] + cntM[i-1][j-1]; //no of tomatoes in current slice
                        if(num_M >= L && w*h - num_M >=L)  {
                            ansH = h, ansW = w;
                            break;
                        }
                    }
                    if(ansH != -1)
                        break;
                }
                if(ansH == -1)
                    continue;
                answer.push_back({i,j,i+ansH-1,j+ansW-1});
                for(int h = 1; h <= ansH; h++)
                  for(int w = 1; w <= ansW; w++)
                        vis[i+h-1][j+w-1] = true;
            }
        }
    }


    for(int i = 0; i < answer.size(); i++){
      bigger(answer[i]);
    }


    // Output
    cout<<answer.size()<<endl;
    for(int i=0; i<answer.size(); i++)
        answer[i].print();

    return 0;
}

