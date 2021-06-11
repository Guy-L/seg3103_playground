defmodule Grades.Calculator do
  
  def percentage_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(%{list: homework})
    avg_labs = avg(%{list: labs})

	mark = calculate_grade(%{lab: avg_labs, hw: avg_homework, mdt: midterm, fin: final})
    round(mark * 100)
  end

  def letter_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(%{list: homework})
    avg_labs = avg(%{list: labs})

    avg_exams = (midterm + final) / 2

    num_labs =
      labs
      |> Enum.reject(fn mark -> mark < 0.25 end)
      |> Enum.count()

    if failed_to_participate?(%{hw: avg_homework, ex: avg_exams, nlab: num_labs}) do
      "EIN"
    else
	  mark = calculate_grade(%{lab: avg_labs, hw: avg_homework, mdt: midterm, fin: final})

      cond do
        mark > 0.895 -> "A+"
        mark > 0.845 -> "A"
        mark > 0.795 -> "A-"
        mark > 0.745 -> "B+"
        mark > 0.695 -> "B"
        mark > 0.645 -> "C+"
        mark > 0.595 -> "C"
        mark > 0.545 -> "D+"
        mark > 0.495 -> "D"
        mark > 0.395 -> "E"
        :else -> "F"
      end
    end
  end

  def numeric_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(%{list: homework})
    avg_labs = avg(%{list: labs})

    avg_exams = (midterm + final) / 2

    num_labs =
      labs
      |> Enum.reject(fn mark -> mark < 0.25 end)
      |> Enum.count()

    if failed_to_participate?(%{hw: avg_homework, ex: avg_exams, nlab: num_labs}) do
      0
    else
      mark = calculate_grade(%{lab: avg_labs, hw: avg_homework, mdt: midterm, fin: final})

      cond do
        mark > 0.895 -> 10
        mark > 0.845 -> 9
        mark > 0.795 -> 8
        mark > 0.745 -> 7
        mark > 0.695 -> 6
        mark > 0.645 -> 5
        mark > 0.595 -> 4
        mark > 0.545 -> 3
        mark > 0.495 -> 2
        mark > 0.395 -> 1
        :else -> 0
      end
    end
  end
  
  def avg(%{list: list}) do
	if Enum.count(list) == 0 do
		0
	else
		Enum.sum(list) / Enum.count(list)
	end
  end
  
  def failed_to_participate?(%{hw: hw, ex: ex, nlab: nlab}) do
	if hw < 0.4 || ex < 0.4 || nlab < 3 do
		true
	else
		false
	end
  end
  
  def calculate_grade(%{lab: lab, hw: hw, mdt: mdt, fin: fin}) do
	0.2 * lab + 0.3 * hw + 0.2 * mdt + 0.3 * fin
  end
end
