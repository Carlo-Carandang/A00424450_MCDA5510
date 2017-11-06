Imports System
Imports System.Collections.Generic
Imports System.ComponentModel.DataAnnotations
Imports System.ComponentModel.DataAnnotations.Schema
Imports System.Data.Entity.Spatial

<Table("Customer")>
Partial Public Class Customer
    Public Property ID As Integer

    Public Property FirstName As String
    Public Property LastName As String
    Public Property StreetNo As String
    Public Property City As String
    Public Property Province As String
    Public Property Country As String
    Public Property PostalCode As String
    Public Property PhoneNo As String
    Public Property Email As String
End Class
