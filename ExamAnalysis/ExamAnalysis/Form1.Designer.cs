namespace ExamAnalysis
{
  partial class Form1
  {
    /// <summary>
    /// Required designer variable.
    /// </summary>
    private System.ComponentModel.IContainer components = null;

    /// <summary>
    /// Clean up any resources being used.
    /// </summary>
    /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
    protected override void Dispose(bool disposing)
    {
      if (disposing && (components != null))
      {
        components.Dispose();
      }
      base.Dispose(disposing);
    }

    #region Windows Form Designer generated code

    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    private void InitializeComponent()
    {
      this.lstScores = new System.Windows.Forms.ListBox();
      this.lblScores = new System.Windows.Forms.Label();
      this.txtFolderPath = new System.Windows.Forms.TextBox();
      this.cmdLoadExam01 = new System.Windows.Forms.Button();
      this.cmdLoadExam02 = new System.Windows.Forms.Button();
      this.cmdLoadExam03 = new System.Windows.Forms.Button();
      this.lstAnalysis = new System.Windows.Forms.ListBox();
      this.lblAnalysis = new System.Windows.Forms.Label();
      this.cmdAnalysis = new System.Windows.Forms.Button();
      this.lblTrend = new System.Windows.Forms.Label();
      this.cmdTrend = new System.Windows.Forms.Button();
      this.lstTrend = new System.Windows.Forms.ListBox();
      this.SuspendLayout();
      // 
      // lstScores
      // 
      this.lstScores.FormattingEnabled = true;
      this.lstScores.ItemHeight = 25;
      this.lstScores.Location = new System.Drawing.Point(26, 57);
      this.lstScores.Name = "lstScores";
      this.lstScores.Size = new System.Drawing.Size(212, 279);
      this.lstScores.TabIndex = 0;
      // 
      // lblScores
      // 
      this.lblScores.AutoSize = true;
      this.lblScores.Location = new System.Drawing.Point(94, 29);
      this.lblScores.Name = "lblScores";
      this.lblScores.Size = new System.Drawing.Size(74, 25);
      this.lblScores.TabIndex = 1;
      this.lblScores.Text = "Scores";
      // 
      // txtFolderPath
      // 
      this.txtFolderPath.BackColor = System.Drawing.Color.Aqua;
      this.txtFolderPath.Location = new System.Drawing.Point(26, 511);
      this.txtFolderPath.Name = "txtFolderPath";
      this.txtFolderPath.Size = new System.Drawing.Size(693, 30);
      this.txtFolderPath.TabIndex = 2;
      this.txtFolderPath.Text = "..\\..\\..\\Spring2017";
      // 
      // cmdLoadExam01
      // 
      this.cmdLoadExam01.BackColor = System.Drawing.Color.Yellow;
      this.cmdLoadExam01.Location = new System.Drawing.Point(26, 351);
      this.cmdLoadExam01.Name = "cmdLoadExam01";
      this.cmdLoadExam01.Size = new System.Drawing.Size(212, 35);
      this.cmdLoadExam01.TabIndex = 3;
      this.cmdLoadExam01.Text = "Exam 01";
      this.cmdLoadExam01.UseVisualStyleBackColor = false;
      this.cmdLoadExam01.Click += new System.EventHandler(this.cmdLoadExam01_Click);
      // 
      // cmdLoadExam02
      // 
      this.cmdLoadExam02.BackColor = System.Drawing.Color.Yellow;
      this.cmdLoadExam02.Location = new System.Drawing.Point(26, 398);
      this.cmdLoadExam02.Name = "cmdLoadExam02";
      this.cmdLoadExam02.Size = new System.Drawing.Size(212, 35);
      this.cmdLoadExam02.TabIndex = 4;
      this.cmdLoadExam02.Text = "Exam 02";
      this.cmdLoadExam02.UseVisualStyleBackColor = false;
      this.cmdLoadExam02.Click += new System.EventHandler(this.cmdLoadExam02_Click);
      // 
      // cmdLoadExam03
      // 
      this.cmdLoadExam03.BackColor = System.Drawing.Color.Yellow;
      this.cmdLoadExam03.Location = new System.Drawing.Point(26, 444);
      this.cmdLoadExam03.Name = "cmdLoadExam03";
      this.cmdLoadExam03.Size = new System.Drawing.Size(212, 35);
      this.cmdLoadExam03.TabIndex = 5;
      this.cmdLoadExam03.Text = "Exam 03";
      this.cmdLoadExam03.UseVisualStyleBackColor = false;
      this.cmdLoadExam03.Click += new System.EventHandler(this.cmdLoadExam03_Click);
      // 
      // lstAnalysis
      // 
      this.lstAnalysis.FormattingEnabled = true;
      this.lstAnalysis.ItemHeight = 25;
      this.lstAnalysis.Location = new System.Drawing.Point(314, 57);
      this.lstAnalysis.Name = "lstAnalysis";
      this.lstAnalysis.Size = new System.Drawing.Size(212, 279);
      this.lstAnalysis.TabIndex = 6;
      // 
      // lblAnalysis
      // 
      this.lblAnalysis.AutoSize = true;
      this.lblAnalysis.Location = new System.Drawing.Point(380, 29);
      this.lblAnalysis.Name = "lblAnalysis";
      this.lblAnalysis.Size = new System.Drawing.Size(86, 25);
      this.lblAnalysis.TabIndex = 7;
      this.lblAnalysis.Text = "Analysis";
      // 
      // cmdAnalysis
      // 
      this.cmdAnalysis.BackColor = System.Drawing.Color.Yellow;
      this.cmdAnalysis.Enabled = false;
      this.cmdAnalysis.Location = new System.Drawing.Point(314, 351);
      this.cmdAnalysis.Name = "cmdAnalysis";
      this.cmdAnalysis.Size = new System.Drawing.Size(212, 35);
      this.cmdAnalysis.TabIndex = 8;
      this.cmdAnalysis.Text = "Analysis";
      this.cmdAnalysis.UseVisualStyleBackColor = false;
      this.cmdAnalysis.Click += new System.EventHandler(this.cmdAnalysis_Click);
      // 
      // lblTrend
      // 
      this.lblTrend.AutoSize = true;
      this.lblTrend.Location = new System.Drawing.Point(685, 29);
      this.lblTrend.Name = "lblTrend";
      this.lblTrend.Size = new System.Drawing.Size(64, 25);
      this.lblTrend.TabIndex = 10;
      this.lblTrend.Text = "Trend";
      // 
      // cmdTrend
      // 
      this.cmdTrend.BackColor = System.Drawing.Color.Yellow;
      this.cmdTrend.Enabled = false;
      this.cmdTrend.Location = new System.Drawing.Point(601, 351);
      this.cmdTrend.Name = "cmdTrend";
      this.cmdTrend.Size = new System.Drawing.Size(212, 35);
      this.cmdTrend.TabIndex = 11;
      this.cmdTrend.Text = "Trend";
      this.cmdTrend.UseVisualStyleBackColor = false;
      this.cmdTrend.Click += new System.EventHandler(this.cmdTrend_Click);
      // 
      // lstTrend
      // 
      this.lstTrend.FormattingEnabled = true;
      this.lstTrend.ItemHeight = 25;
      this.lstTrend.Location = new System.Drawing.Point(601, 57);
      this.lstTrend.Name = "lstTrend";
      this.lstTrend.Size = new System.Drawing.Size(212, 279);
      this.lstTrend.TabIndex = 12;
      // 
      // Form1
      // 
      this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
      this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
      this.BackColor = System.Drawing.Color.Aqua;
      this.ClientSize = new System.Drawing.Size(847, 554);
      this.Controls.Add(this.lstTrend);
      this.Controls.Add(this.cmdTrend);
      this.Controls.Add(this.lblTrend);
      this.Controls.Add(this.cmdAnalysis);
      this.Controls.Add(this.lblAnalysis);
      this.Controls.Add(this.lstAnalysis);
      this.Controls.Add(this.cmdLoadExam03);
      this.Controls.Add(this.cmdLoadExam02);
      this.Controls.Add(this.cmdLoadExam01);
      this.Controls.Add(this.txtFolderPath);
      this.Controls.Add(this.lblScores);
      this.Controls.Add(this.lstScores);
      this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
      this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
      this.Margin = new System.Windows.Forms.Padding(4);
      this.Name = "Form1";
      this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
      this.Text = "Exam Analysis";
      this.ResumeLayout(false);
      this.PerformLayout();

    }

    #endregion

    private System.Windows.Forms.ListBox lstScores;
    private System.Windows.Forms.Label lblScores;
    private System.Windows.Forms.TextBox txtFolderPath;
    private System.Windows.Forms.Button cmdLoadExam01;
    private System.Windows.Forms.Button cmdLoadExam02;
    private System.Windows.Forms.Button cmdLoadExam03;
    private System.Windows.Forms.ListBox lstAnalysis;
    private System.Windows.Forms.Label lblAnalysis;
    private System.Windows.Forms.Button cmdAnalysis;
    private System.Windows.Forms.Label lblTrend;
    private System.Windows.Forms.Button cmdTrend;
    private System.Windows.Forms.ListBox lstTrend;
  }
}

