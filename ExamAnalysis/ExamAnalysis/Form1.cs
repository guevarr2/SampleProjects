using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ExamAnalysis
{
  public partial class Form1 : Form
  {
    public Form1()
    {
      InitializeComponent();
    }

    Microsoft.FSharp.Collections.FSharpList<int> scores;

    private void cmdLoadExam01_Click(object sender, EventArgs e)
    {
      this.lstScores.Items.Clear();
      this.lstAnalysis.Items.Clear();
      this.lstTrend.Items.Clear();

      string folder = this.txtFolderPath.Text;
      string filepath = System.IO.Path.Combine(folder, "Exam01.txt");

      scores = MyLibrary.InputScores(filepath);

      foreach (var score in scores)
      {
        this.lstScores.Items.Add(score);
      }

      this.cmdAnalysis.Enabled = true;
      this.cmdTrend.Enabled = true;
    }

    private void cmdLoadExam02_Click(object sender, EventArgs e)
    {
      this.lstScores.Items.Clear();
      this.lstAnalysis.Items.Clear();
      this.lstTrend.Items.Clear();

      string folder = this.txtFolderPath.Text;
      string filepath = System.IO.Path.Combine(folder, "Exam02.txt");

      scores = MyLibrary.InputScores(filepath);

      foreach (var score in scores)
      {
        this.lstScores.Items.Add(score);
      }

      this.cmdAnalysis.Enabled = true;
      this.cmdTrend.Enabled = true;
    }

    private void cmdLoadExam03_Click(object sender, EventArgs e)
    {
      this.lstScores.Items.Clear();
      this.lstAnalysis.Items.Clear();
      this.lstTrend.Items.Clear();

      string folder = this.txtFolderPath.Text;
      string filepath = System.IO.Path.Combine(folder, "Exam03.txt");

      scores = MyLibrary.InputScores(filepath);

      foreach (var score in scores)
      {
        this.lstScores.Items.Add(score);
      }

      this.cmdAnalysis.Enabled = true;
      this.cmdTrend.Enabled = true;
    }

    private void cmdAnalysis_Click(object sender, EventArgs e)
    {
      this.lstAnalysis.Items.Clear();

      var N = MyLibrary.NumScores(scores);
      var Min = MyLibrary.FindMin(scores);
      var Max = MyLibrary.FindMax(scores);
      var Avg = MyLibrary.Average(scores);
      var Median = MyLibrary.Median(scores);
      var StdDev = MyLibrary.StdDev(scores);

      this.lstAnalysis.Items.Add("N: " + N);
      this.lstAnalysis.Items.Add("Min: " + Min);
      this.lstAnalysis.Items.Add("Max: " + Max);
      this.lstAnalysis.Items.Add("Avg: " + Avg);
      this.lstAnalysis.Items.Add("Median: " + Median);
      this.lstAnalysis.Items.Add("StdDev: " + StdDev);

      var Histogram = MyLibrary.Histogram(scores);

      this.lstAnalysis.Items.Add("Histogram:");
      this.lstAnalysis.Items.Add("  90-100: " + Histogram.ElementAt(0));
      this.lstAnalysis.Items.Add("  80-89: " + Histogram.ElementAt(1));
      this.lstAnalysis.Items.Add("  70-79: " + Histogram.ElementAt(2));
      this.lstAnalysis.Items.Add("  60-69: " + Histogram.ElementAt(3));
      this.lstAnalysis.Items.Add("  0-59: " + Histogram.ElementAt(4));
    }

    private void cmdTrend_Click(object sender, EventArgs e)
    {
      this.lstTrend.Items.Clear();

      string folder = this.txtFolderPath.Text;
      string filepath1 = System.IO.Path.Combine(folder, "Exam01.txt");
      string filepath2 = System.IO.Path.Combine(folder, "Exam02.txt");
      string filepath3 = System.IO.Path.Combine(folder, "Exam03.txt");

      var L1 = MyLibrary.InputScores(filepath1);
      var L2 = MyLibrary.InputScores(filepath2);
      var L3 = MyLibrary.InputScores(filepath3);

      var R = MyLibrary.Trend(L1, L2, L3);

      int i = 0;

      foreach (var trend in R)
      {
        var s = string.Format("{0},{1},{2}: {3}",
          L1.ElementAt(i),
          L2.ElementAt(i),
          L3.ElementAt(i),
          trend);

        this.lstTrend.Items.Add(s);

        i++;
      }
    }

  }
}
