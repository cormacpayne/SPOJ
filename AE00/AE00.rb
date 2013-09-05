t = gets.to_i
i = 0
sol = t
for q in 2..t do
  for i in q..(t-1) do
    if i*q > t
      break
    end
    sol+=1    
  end
  if i == q
    break
  end
end
puts sol
