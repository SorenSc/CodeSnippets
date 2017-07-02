# Data has a large number of columns and multiple columns shall be for instance visualized at once, but not all 
# because it would be to confusing.
# You can customize the function by changing the 'function_call' to your customized function. After each press 
# of enter the summary for the next ten (default value) columns are displayed.
summarize_large_dataset = function(data, number_of_columns_per_round = 10){
  number_of_columns = length(data)
  iterator = seq(1, 
                 number_of_columns, 
                 number_of_columns_per_round)
  dataset_length = nrow(data)
  
  for(i in iterator){
    if(i + number_of_columns_per_round - 1 < number_of_columns){
      function_call(data[,i:(i+number_of_columns_per_round - 1)])
      readline(prompt="Press [enter] to continue")
    }else{
      function_call(data[,i:number_of_columns])
    }
  }
}