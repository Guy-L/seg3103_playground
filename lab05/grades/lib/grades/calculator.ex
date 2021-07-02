defmodule Grades.Calculator do
  
  def percentage_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    :rand.uniform(100)
  end

  def letter_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
	mark = :rand.uniform()

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
      mark >= 0 -> "F"
      :else -> "EIN"
    end
  end

  def numeric_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
	:rand.uniform(10)
  end
end
