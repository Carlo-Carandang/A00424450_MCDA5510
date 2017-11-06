'  File ContactForm
'  Sample code was taken from:
'  'https://docs.microsoft.com/en-us/dotnet/visual-basic/developing-apps/programming/drives-directories-files/how-to-read-from-comma-delimited-text-files
' 
' Assignment #2
' Due Oct 16, 2017
'
' Requirement 1: Expand on this form to display information in database that displays the following fields
' First Name (TextBox)
' Last Name (TextBox)
' Street Number (TextBox)
' City (TextBox) 
' Province (TextBox)
' Country (TextBox)
' Postal Code  (TextBox)( https://stackoverflow.com/questions/16614648/canadian-postal-code-regex)
' Phone Number (TextBox)( http://www.visual-basic-tutorials.com/Tutorials/Strings/validate-phone-number-in-visual-basic.htm)
' email Address (TextBox)( https://stackoverflow.com/questions/1331084/how-do-i-validate-email-address-formatting-with-the-net-framework)
'
' Add Next and Prevous Buttons to navigate through the database ( handle index 0 and max index)
' Display the current primary key of the database in a textbox or label
' Add a Status TextBox and dispaly any formatting errors that are encoutered, 
' If multiple errors exist only show last one.

' Requirement 2: Expand on the below example to create a import the contents of the CSV file 
' created in Assignment1, read the data into entity classes and save data to database.  
' After import Next and Prev buttons should work.
'
'
' Please always try to write clean And readable code
' Here Is a good reference doc http://ricardogeek.com/docs/clean_code.html  
' Submit to Bitbucket under Assignment2

Imports System.Text.RegularExpressions

Public Class ContactForm
    Dim index As Integer = 0

    Dim allData As New Dictionary(Of Integer, Customer)

    Dim RowIdxFirstName As Integer = 0
    Dim RowIdxLastName As Integer = 1
    Dim RowIdxStreetNo As Integer = 2
    Dim RowIdxCity As Integer = 3
    Dim RowIdxProvince As Integer = 4
    Dim RowIdxCountry As Integer = 5
    Dim RowIdxPostalCode As Integer = 6
    Dim RowIdxPhoneNo As Integer = 7
    Dim RowIdxEmail As Integer = 8

    Private Sub NextButton_Click(sender As Object, e As EventArgs) Handles bn_next.Click
        index = index + 1
        UpdateData(index)
    End Sub

    Private Sub UpdateData(index As Integer)
        Dim personData As Customer = Nothing
        If allData.TryGetValue(index, personData) Then
            tb_index.Text = index
            fname.Text = personData.FirstName
            lname.Text = personData.LastName
            street.Text = personData.StreetNo
            city.Text = personData.City
            province.Text = personData.Province
            country.Text = personData.Country
            postalcode.Text = personData.PostalCode
            phone.Text = personData.PhoneNo
            email.Text = personData.Email
        End If
    End Sub

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        LoadFromDatabase()
    End Sub

    Private Sub LoadFromDatabase()
        allData.Clear()
        Dim index As Integer = 0
        Using context As New Model2
            Dim listOfCustomers = context.Customers.ToList
            For Each cust As Customer In listOfCustomers
                allData.Add(index, cust)
                UpdateData(index)
                index = index + 1
            Next
        End Using
    End Sub

    Private Sub LoadFromCSVFile(csvFile As String)
        Using MyReader As New Microsoft.VisualBasic.
                              FileIO.TextFieldParser(
                                csvFile)
            MyReader.TextFieldType = FileIO.FieldType.Delimited
            MyReader.SetDelimiters(",")
            Dim currentRow As String()
            Dim rowNumber As Integer = 0
            Dim postal_code As Boolean
            Dim phone As Boolean
            Dim mail As Boolean
            While Not MyReader.EndOfData
                Try
                    currentRow = MyReader.ReadFields()
                    Dim rowData As New ArrayList
                    Dim currentField As String
                    Dim NewCust As New Customer
                    For Each currentField In currentRow
                        rowData.Add(currentField)
                    Next

                    NewCust.FirstName = rowData.Item(RowIdxFirstName)
                    NewCust.LastName = rowData.Item(RowIdxLastName)
                    NewCust.StreetNo = rowData.Item(RowIdxStreetNo)
                    NewCust.City = rowData.Item(RowIdxCity)
                    NewCust.Province = rowData.Item(RowIdxProvince)
                    NewCust.Country = rowData.Item(RowIdxCountry)
                    NewCust.PostalCode = rowData.Item(RowIdxPostalCode)
                    NewCust.PhoneNo = rowData.Item(RowIdxPhoneNo)
                    NewCust.Email = rowData.Item(RowIdxEmail)

                    'skip the header row from being written to the database- otherwise it saves the rest of the rows
                    If (rowNumber <> 0) Then
                        Using context As New Model2
                            context.Customers.Add(NewCust)
                            context.SaveChanges()
                        End Using

                        'validating postal code
                        postal_code = CanBeValidCanadianPostalCode(NewCust.PostalCode)
                        If Not postal_code Then
                            TextBox2.Text = "Invalid postal code"
                        End If

                        'validating phone
                        phone = IsValidPhone(NewCust.PhoneNo)
                        If Not phone Then
                            TextBox2.Text = "Invalid phone number"
                        End If

                        'validating mail
                        mail = IsValidEmailFormat(NewCust.Email)
                        If Not mail Then
                            TextBox2.Text = "Invalid email address"
                        End If

                    End If

                Catch ex As Microsoft.VisualBasic.FileIO.MalformedLineException
                    TextBox2.Text = "Line " & ex.Message & "is not valid and will be skipped."
                End Try
                rowNumber = rowNumber + 1
            End While
        End Using
        LoadFromDatabase()
    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        index = index - 1
        UpdateData(index)
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        ImportCSVFile()
    End Sub

    Private Sub ImportCSVFile()
        Dim importFile As String = ""
        Dim fd As OpenFileDialog = New OpenFileDialog()
        fd.Title = "Select CSV file to import"
        fd.Filter = "All files (*.*)|*.*|All files (*.*)|*.*"
        fd.FilterIndex = 2
        fd.RestoreDirectory = True
        If fd.ShowDialog() = DialogResult.OK Then
            importFile = fd.FileName
        Else
        End If
        Dim csvFile As String = "C:\Users\Carlo\source\Repos\src5\WindowsApp1\customers.csv"
        LoadFromCSVFile(csvFile)
    End Sub

    Private Sub btnExit_Click(sender As Object, e As EventArgs) Handles btnExit.Click
        Me.Close()
    End Sub

    Private Function CanBeValidCanadianPostalCode(ByVal q As String) As Boolean
        Return Regex.IsMatch(q.Replace(" ", "").ToUpper, "([A-Z]\d){3}")
    End Function

    Private Function IsValidPhone(ByVal r As String) As Boolean
        Return Regex.IsMatch(r, "\d{3}-\d{3}-\d{4}")
    End Function

    Private Function IsValidEmailFormat(ByVal s As String) As Boolean
        Return Regex.IsMatch(s, "^([0-9a-zA-Z]([-\.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$")
    End Function

End Class
