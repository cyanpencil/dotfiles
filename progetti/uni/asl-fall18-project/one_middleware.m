pkg load queueing
clear

function print_line(label, idx, aggr)
  printf("\t%s", label)
  for i = 1:length(aggr(idx,:))
    printf("%10.3f", aggr(idx,i)) 
  endfor
  printf("\n")
endfunction

function print_dev(name, aggr)
  printf("%s\n", name)
  print_line("U", 1, aggr)
  print_line("R", 2, aggr)
  print_line("Q", 3, aggr)
endfunction






Visit_splits = [1, 1, 1 , 1];
client=[6, 12, 24, 48, 96, 192];
worker_m = 64
memcached_service = 1. / 16000;
Z_think_time = 0;


ping = 0.001955;


memtier_response_time = (14.8844 / 1000);
mw_response_time = (12.860476 / 1000);
mw_mainthread = (0.012 / 1000);


network_thread_service =  memtier_response_time - ping - mw_response_time + mw_mainthread;
printf("Network service:%f\n", network_thread_service);

worker_service = 0.0966646201507967 / 1000 ;







counter =0 ;
for N = client
  counter += 1;
  
  QQ = { qnmknode("-/g/inf", ping),
       qnmknode("m/m/m-fcfs", network_thread_service, 1),
       qnmknode("m/m/m-fcfs", worker_service, worker_m)
       qnmknode("m/m/m-fcfs", memcached_service, 1)};
       
  [U R Q X] = qnsolve("closed", N, QQ, Visit_splits, Z_think_time);

  NT_aggr(:,counter) = [U(2), R(2)*1000, Q(2)];
  WT_aggr(:,counter) = [U(3), R(3)*1000, Q(3)];
  MC_aggr(:,counter) = [U(4), R(4)*1000, Q(4)];
  
endfor



print_dev("net-thread", NT_aggr)
print_dev("worker-thread", WT_aggr)
print_dev("memcached", MC_aggr)



%           write only
% one middleware
X = [3264.3033333333333, 6237.97, 8846.643333333335, 10688.693333333333, 12467.846666666665, 12952.863333333333];
% two middlewares
%X = [3342.97, 6410.256666666667, 8643.366666666667, 11576.256666666668, 14134.893333333332, 15426.133333333333]



%           read only 
% one middleware
%X = [2908.3233333333333, 2946.763333333334, 2945.33, 2925.1699999999996, 2965.6066666666666, 2926.3199999999997]
% two middlewares
%X = [2769.986666666667, 2939.0733333333333, 2944.4933333333333, 2982.1233333333334, 2948.876666666667, 2940.8033333333333]

