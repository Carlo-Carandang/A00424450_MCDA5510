Imports System
Imports System.Data.Entity
Imports System.ComponentModel.DataAnnotations.Schema
Imports System.Linq

Partial Public Class Model2
    Inherits DbContext

    Public Sub New()
        MyBase.New("name=Model2")
    End Sub

    Public Overridable Property Customers As DbSet(Of Customer)

    Protected Overrides Sub OnModelCreating(ByVal modelBuilder As DbModelBuilder)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.FirstName) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.LastName) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.StreetNo) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.City) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.Province) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.Country) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.PostalCode) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.PhoneNo) _
            .IsUnicode(False)
        modelBuilder.Entity(Of Customer)() _
            .Property(Function(e) e.Email) _
            .IsUnicode(False)
    End Sub
End Class
